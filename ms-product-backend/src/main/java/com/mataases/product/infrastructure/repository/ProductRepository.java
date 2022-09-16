package com.mataases.product.infrastructure.repository;

import com.mataases.product.domain.entity.Category;
import com.mataases.product.domain.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository  extends JpaRepository<Product, Long> {

    public List<Product> findByCategory(Category category);
    public Product findByNumberProduct(String numberProduct);
}
