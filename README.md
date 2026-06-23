# BTEC E-Commerce Platform

Sifatli va production-ready E-Commerce veb-saytining to'liq Spring Boot loyihasi.

## Xususiyatlar

✅ Complete CRUD API  
✅ PostgreSQL baza  
✅ Docker va CI/CD  
✅ Responsive design  
✅ Clean Architecture  

## Talablar

- Java 17+
- Maven 3.9+
- Docker (opsiyonal)
- PostgreSQL yoki Neon.tech

## Lokal o'rnatish

```bash
# 1. Repository klonlang
git clone <repo-url>
cd btec-ecommerce

# 2. Environment variables'ni o'rnatish
export DB_URL="jdbc:postgresql://localhost:5432/ecommerce"
export DB_USER="postgres"
export DB_PASSWORD="your-password"

# 3. Application ishga tushirish
mvn spring-boot:run

# 4. Browser'da bukish
http://localhost:8080
```

## Docker bilan

```bash
docker build -t ecommerce-app .
docker run -p 8080:8080 \
  -e DB_URL="jdbc:postgresql://host.docker.internal:5432/ecommerce" \
  -e DB_USER="postgres" \
  -e DB_PASSWORD="password" \
  ecommerce-app
```

## Render.com'ga Deploy

1. Render.com'da yangi Web Service yarating
2. GitHub repository ulanish
3. Environment variables'ni o'rnatish:
   - `DB_URL` - Neon.tech database URL
   - `DB_USER` - Database foydalanuvchi
   - `DB_PASSWORD` - Database parol

## API Endpoints

| Method | Endpoint | Ta'rif |
|--------|----------|--------|
| GET | `/api/products` | Barcha mahsulotlarni ol |
| GET | `/api/products/{id}` | Bitta mahsulotni ol |
| GET | `/api/products/category/{category}` | Kategoriya bo'yicha |
| GET | `/api/products/search?query=...` | Qidirish |
| POST | `/api/products` | Yangi mahsulot yarat |
| PUT | `/api/products/{id}` | Mahsulotni yangilash |
| DELETE | `/api/products/{id}` | Mahsulotni o'chirish |

## Tech Stack

- **Backend**: Spring Boot 3.2, Spring Data JPA
- **Database**: PostgreSQL 15+
- **Frontend**: HTML5, CSS3, Vanilla JavaScript
- **Containerization**: Docker
- **CI/CD**: GitHub Actions
- **Deployment**: Render.com

## Author

BTEC Learning Platform  
2024

