package com.betvictor.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeService {

    Employe getProduct(String name);

    Page<Employe> getAll(Pageable pageable);
}
