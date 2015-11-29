package com.betvictor.data;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface ProductRepository extends Repository<Product, Long> {

    Page<Product> findAll(Pageable pageable);
}
