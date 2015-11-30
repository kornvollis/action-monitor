package com.betvictor.data;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface EmployeRepository extends Repository<Employe, Long> {

    Page<Employe> findAll(Pageable pageable);
}
