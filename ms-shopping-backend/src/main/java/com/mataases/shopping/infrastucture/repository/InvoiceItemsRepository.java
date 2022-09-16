package com.mataases.shopping.infrastucture.repository;

import com.mataases.shopping.domain.entity.InvoiceItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceItemsRepository extends JpaRepository<InvoiceItem,Long> {
}
