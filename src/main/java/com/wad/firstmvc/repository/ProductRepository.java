package com.wad.firstmvc.repository;

import com.wad.firstmvc.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Product findByCategory(String category);
}
