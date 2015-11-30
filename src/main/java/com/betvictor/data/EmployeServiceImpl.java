package com.betvictor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("productService")
@Transactional
class EmployeServiceImpl implements EmployeService {

    private final EmployeRepository employeRepository;

    @Autowired
    public EmployeServiceImpl(EmployeRepository employeRepository) {
        this.employeRepository = employeRepository;
    }

    @Override
    public Employe getProduct(String name) {
        return null;
    }

    @Override
    public Page<Employe> getAll(Pageable pageable) {
        return this.employeRepository.findAll(null);
    }
}
