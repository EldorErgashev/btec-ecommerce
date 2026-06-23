package com.example.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * WebConfig — brauzerdan keladigan sahifa so'rovlarini
 * src/main/resources/static/ papkasidagi HTML fayllariga yo'naltiradi.
 *
 * Yo'naltirish jadvali:
 *   GET /            → /index.html    (bosh sahifa)
 *   GET /products    → /products.html (mahsulotlar sahifasi)
 *
 * REST API yo'llari bu yerga tegmaydi — ular ProductController da:
 *   GET /api/products        → JSON ro'yxat
 *   GET /api/products/{id}   → JSON bitta mahsulot
 *   va h.k.
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Bosh sahifa: http://your-app.onrender.com/
        registry.addViewController("/").setViewName("forward:/index.html");

        // Mahsulotlar sahifasi: http://your-app.onrender.com/products
        registry.addViewController("/products").setViewName("forward:/products.html");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Static fayllarni (CSS, JS, HTML) classpath ichidan serve qilish
        // Spring Boot buni avtomatik qiladi, lekin Docker muhitida explicit bo'lishi ishonchli
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/")
                .setCachePeriod(3600);
    }
}
