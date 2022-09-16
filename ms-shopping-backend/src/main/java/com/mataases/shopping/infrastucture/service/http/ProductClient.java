package com.mataases.shopping.infrastucture.service.http;

import com.mataases.shopping.domain.dto.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-product" , url = "http://ms-product-backend-svc:8092")
public interface ProductClient {

    @GetMapping(value = "/products/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable("id") Long id);
}
