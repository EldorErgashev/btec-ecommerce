package com.example.ecommerce.repository;

import com.example.ecommerce.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    
    List<Product> findByCategory(String category);
    
    List<Product> findByNameIgnoreCaseContaining(String name);
    
    @Query("SELECT p FROM Product p WHERE p.category = :category ORDER BY p.price ASC")
    List<Product> findByCategoryOrderByPrice(@Param("category") String category);
    
    @Query("SELECT p FROM Product p WHERE p.stock > 0 ORDER BY p.createdAt DESC")
    List<Product> findAvailableProducts();
}
