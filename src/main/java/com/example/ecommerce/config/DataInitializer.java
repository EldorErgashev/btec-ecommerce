package com.example.ecommerce.config;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.math.BigDecimal;
import java.util.Arrays;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class DataInitializer {
    
    @Bean
    public CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            if (productRepository.count() == 0) {
                log.info("🔄 Namunaviy mahsulotlarni qo'shmoqda...");
                
                Product[] products = {
                    new Product(null, "USB-C Sim", "Yuqori tezlik, uzun turinuvchi USB-C sim", 
                        new BigDecimal("45000"), "simlar", 
                        "https://via.placeholder.com/250?text=USB-C+Cable", 50, null, null),
                    
                    new Product(null, "Lightning Sim", "Apple qurilmalari uchun MFi sertifikatlangan", 
                        new BigDecimal("55000"), "simlar", 
                        "https://via.placeholder.com/250?text=Lightning+Cable", 30, null, null),
                    
                    new Product(null, "65W Quvvatlash Bloki", "Tez quvvatlash texnologiyasi, 65W imkoniyat", 
                        new BigDecimal("150000"), "quvvatlash", 
                        "https://via.placeholder.com/250?text=65W+Charger", 20, null, null),
                    
                    new Product(null, "30W Quvvatlash Bloki", "O'rtacha quvvatlash, kompakt dizayn", 
                        new BigDecimal("85000"), "quvvatlash", 
                        "https://via.placeholder.com/250?text=30W+Charger", 40, null, null),
                    
                    new Product(null, "Wireless Naushniklar", "ANC texnologiyasi bilan, 30 soat batareyasi", 
                        new BigDecimal("450000"), "naushniklar", 
                        "https://via.placeholder.com/250?text=Wireless+Earbuds", 15, null, null),
                    
                    new Product(null, "Kabel Naushniklar", "Sof ovoz, ergonom dizayn, og'irlik 180g", 
                        new BigDecimal("280000"), "naushniklar", 
                        "https://via.placeholder.com/250?text=Wired+Headphones", 25, null, null)
                };
                
                productRepository.saveAll(Arrays.asList(products));
                log.info("✅ 6 ta mahsulot qo'shildi!");
            }
        };
    }
}
