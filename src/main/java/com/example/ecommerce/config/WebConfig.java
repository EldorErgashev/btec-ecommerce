package com.example.ecommerce.config; // O'zingizning paket nomingizga moslang

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        // Asosiy / sahifa kelganda static/index.html faylini ochadi
        registry.addViewController("/").setViewName("forward:/index.html");

        // /products kelganda static/products.html faylini ochadi
        registry.addViewController("/products").setViewName("forward:/products.html");
    }
}