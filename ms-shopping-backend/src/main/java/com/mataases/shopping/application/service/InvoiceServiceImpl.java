package com.mataases.shopping.application.service;

import com.mataases.shopping.infrastucture.service.http.CustomerClient;
import com.mataases.shopping.infrastucture.service.http.ProductClient;
import com.mataases.shopping.domain.entity.Invoice;
import com.mataases.shopping.domain.entity.InvoiceItem;
import com.mataases.shopping.domain.dto.Customer;
import com.mataases.shopping.domain.dto.Product;
import com.mataases.shopping.application.error.exception.ResourceNotFoundException;
import com.mataases.shopping.infrastucture.repository.InvoiceRepository;
import com.mataases.shopping.application.util.Constant;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvoiceServiceImpl implements InvoiceService {


    private final InvoiceRepository invoiceRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;




    @Override
    public List<Invoice> findInvoiceAll() {
        return  invoiceRepository.findAll();
    }


    @Override
    public Invoice createInvoice(Invoice invoice) {
        Invoice invoiceDB = invoiceRepository.findByNumberInvoice ( invoice.getNumberInvoice () );
        if (invoiceDB !=null){
            return  invoiceDB;
        }
        invoice.setState( Constant.STATE_CREATED );
        invoice.setCreatedBy ( "admin" );
        return invoiceRepository.save(invoice);
    }


    @Override
    public Invoice updateInvoice(Invoice invoice) {
        Invoice invoiceDB = getInvoice(invoice.getId());

        invoiceDB.setCustomerId(invoice.getCustomerId());
        invoiceDB.setDescription(invoice.getDescription());
        invoiceDB.setNumberInvoice(invoice.getNumberInvoice());
        invoiceDB.getItems().clear();
        invoiceDB.setItems(invoice.getItems());

        invoiceDB.setUpdatedBy ( "admin" );
        return invoiceRepository.save(invoiceDB);
    }


    @Override
    public Invoice deleteInvoice(Invoice invoice) {
        Invoice invoiceDB =this.getInvoice(invoice.getId());

        invoiceDB.setState(Constant.STATE_DELETED);
        return invoiceRepository.save(invoiceDB);
    }

    @Override
    public Invoice getInvoice(Long id) {
        Invoice invoice= invoiceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found Invoice with id = " + id));

        ResponseEntity<Customer> rsCustomer=  customerClient.getCustomer(invoice.getCustomerId());

        if ( rsCustomer.getStatusCode().equals( HttpStatus.OK) ){
            invoice.setCustomer(rsCustomer.getBody());
        }else {
            log.error( rsCustomer.getStatusCode().toString() );
        }
        List<InvoiceItem> items = invoice.getItems().stream()
                .map(invoiceItem -> {
                    ResponseEntity<Product> rsProduct = productClient.getProduct(invoiceItem.getProductId());
                    if ( rsProduct.getStatusCode().equals( HttpStatus.OK) ){
                        invoiceItem.setProduct(rsProduct.getBody());
                    }else {
                        log.error( rsProduct.getStatusCode().toString() );
                    }
                    return invoiceItem;
                }).collect(Collectors.toList());

        invoice.setItems(items);

        return invoice;
    }

}
