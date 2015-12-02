package com.betvictor.controller;

import com.betvictor.data.EmployeService;
import com.betvictor.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeService employeService;

    @RequestMapping("/employee")
    public String home(Map<String, Object> model) {

        List<Employee> employees = employeService.getAll(null).getContent();

        model.put("employes", employees);
        return "employee_list";
    }

    @RequestMapping(path = "/employee/delete/{id}",  method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) {

        employeService.delete(id);

        return "deleted";
    }

    @RequestMapping(path = "/employee/add",  method = RequestMethod.POST)
    @ResponseBody
    public Employee add(@ModelAttribute Employee employee) {

        employeService.save(employee);

        return employee;
    }

    @RequestMapping(path = "/employee/update/{id}",  method = RequestMethod.POST)
    @ResponseBody
    public Employee update(@PathVariable Long id,  @RequestBody Employee employee) {

        Employee emp = employeService.findOne(id);

        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());

        employeService.save(emp);

        return emp;
    }
}