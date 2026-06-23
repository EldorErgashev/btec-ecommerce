# ============================================================
# Multi-stage Dockerfile — Spring Boot + Static Files
# Render.com (In-cloud) va mahalliy Docker bilan mos keladi
# ============================================================

# ── Stage 1: Build ──────────────────────────────────────────
FROM maven:3.9-eclipse-temurin-17-alpine AS builder

WORKDIR /app

# 1. Avval faqat pom.xml ni ko'chirib, dependency larni yukla
#    (bu qatlamni cache da saqlaydi — src o'zgarganda qayta yuklamaydi)
COPY pom.xml .
RUN mvn dependency:go-offline -q

# 2. Barcha source kodini ko'chir
#    MUHIM: src/main/resources/static/ ichidagi HTML/CSS/JS ham shu yerda
COPY src ./src

# 3. Fat JAR yaratish (-DskipTests tezlashtiradi)
#    spring-boot-maven-plugin barcha static fayllarni JAR ichiga o'radi:
#    BOOT-INF/classes/static/index.html
#    BOOT-INF/classes/static/products.html
#    BOOT-INF/classes/static/css/style.css
#    BOOT-INF/classes/static/js/app.js
RUN mvn clean package -DskipTests -q

# ── Stage 2: Runtime ─────────────────────────────────────────
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Builder bosqichidagi tayyor JAR ni ko'chir
# Bu JAR ichida barcha static fayllar mavjud — alohida ko'chirish shart emas
COPY --from=builder /app/target/ecommerce-app-*.jar app.jar

# Render.com PORT muhit o'zgaruvchisini ishlatadi (odatda 10000)
# application.properties da: server.port=${PORT:8080}
EXPOSE 8080

# Sog'liq tekshiruvi — /api/products API endpoint ni tekshiradi
HEALTHCHECK --interval=30s --timeout=10s --start-period=60s --retries=3 \
    CMD wget --quiet --tries=1 --spider http://localhost:${PORT:-8080}/api/products || exit 1

# JVM xotira chegaralari (Render free tier uchun optimallashtirilgan)
ENV JAVA_OPTS="-Xmx512m -Xms256m -XX:+UseContainerSupport"

# Ilovani ishga tushirish
ENTRYPOINT ["sh", "-c", "java $JAVA_OPTS -jar app.jar"]
