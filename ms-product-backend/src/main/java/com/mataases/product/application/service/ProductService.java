package com.mataases.product.application.service;

import com.mataases.product.domain.entity.Category;
import com.mataases.product.domain.entity.Product;

import java.util.List;

public interface ProductService {
    public List<Product> listAllProduct();
    public Product getProduct(Long id);

    public Product createProduct(Product product);
    public Product updateProduct(Product product);
    public  Product deleteProduct(Long id);
    public List<Product> findByCategory(Category category);
    public Product updateStock(Long id, Double quantity);

    public Category createCategory(Category category);
    public Category updateCategory(Category category);
    public Category getCategory(Long id);
    public void deleteCategory(Long id);
    public List<Category> findCategoryAll();
}