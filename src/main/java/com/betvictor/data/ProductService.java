package com.betvictor.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    Product getProduct(String name);

    Page<Product> getAllProducts(Pageable pageable);
}
