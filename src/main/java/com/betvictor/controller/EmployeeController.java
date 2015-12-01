package com.betvictor.controller;

import com.betvictor.data.EmployeService;
import com.betvictor.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    EmployeService employeService;

    @RequestMapping(path = "/delete/{id}",  method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) {

        employeService.delete(id);

        return "deleted";
    }

    @RequestMapping(path = "/add",  method = RequestMethod.POST)
    @ResponseBody
    public Employee add(@ModelAttribute Employee employee) {

        employeService.save(employee);

        return employee;
    }
}