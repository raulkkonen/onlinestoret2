package com.mataases.customer.infrastructure.repository;


import com.mataases.customer.domain.entity.Customer;
import com.mataases.customer.domain.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository  extends JpaRepository<Customer,Long> {

        public Customer findByNumberID(String numberID);
        public List<Customer> findByLastName(String lastName);
        public List<Customer> findByRegion(Region region);
}
