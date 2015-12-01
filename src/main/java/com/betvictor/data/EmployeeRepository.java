package com.betvictor.data;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.Repository;

interface EmployeeRepository extends Repository<Employee, Long> {

    Employee save(Employee entity);

    void delete(Employee deleted);

    Employee findById(Long id);

    Page<Employee> findAll(Pageable pageable);


}
