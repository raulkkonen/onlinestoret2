package com.mataases.shopping.infrastucture.service.http;

import com.mataases.shopping.domain.dto.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ms-customer" , url = "http://ms-customer-backend-svc:8092")

public interface CustomerClient {
    @GetMapping(value = "/customers/{id}")
    public ResponseEntity<Customer> getCustomer(@PathVariable("id") long id) ;
}
