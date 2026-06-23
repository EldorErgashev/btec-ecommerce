package com.example.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Asosiy root (/) manzil so'ralganda static/index.html sahifasini ochadi
        registry.addViewController("/").setViewName("forward:/index.html");

        // /products manzili so'ralganda static/products.html sahifasini ochadi
        registry.addViewController("/products").setViewName("forward:/products.html");
    }
}