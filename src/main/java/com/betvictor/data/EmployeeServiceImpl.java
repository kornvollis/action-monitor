package com.betvictor.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("productService")
@Transactional
class EmployeeServiceImpl implements EmployeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee save(Employee employee) {
        employeeRepository.save(employee);
        return employee;
    }

    @Override
    public Employee findOne(Long primaryKey) {
        return employeeRepository.findById(primaryKey);
    }

    @Override
    public Employee delete(Long id) {
        Employee employee = employeeRepository.findById(id);
        employeeRepository.delete(employee);

        return employee;
    }

    @Override
    public Page<Employee> getAll(Pageable pageable) {
        return this.employeeRepository.findAll(null);
    }


}
