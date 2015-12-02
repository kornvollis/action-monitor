package com.betvictor.data;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeService {

    Employee save(Employee employee);

    Employee findOne(Long primaryKey);

    Employee delete(Long id);

    Employee getProduct(String name);

    Page<Employee> getAll(Pageable pageable);


}
