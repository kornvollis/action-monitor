package com.betvictor.controller;

import com.betvictor.WebSocketHandler;
import com.betvictor.data.EmployeService;
import com.betvictor.data.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.socket.TextMessage;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    @Autowired
    EmployeService employeService;

    @Autowired
    WebSocketHandler webSocketHandler;

    @RequestMapping(value = { "/","/employee" })
    public String show(Map<String, Object> model) {

        List<Employee> employees = employeService.getAll(null).getContent();

        model.put("employes", employees);
        return "home";
    }

    @RequestMapping(path = "/employee/delete/{id}",  method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) {

        Employee emp = employeService.findOne(id);
        employeService.delete(id);

        webSocketHandler.broadcastDelete(emp);

        return "deleted";
    }

    @RequestMapping(path = "/employee/add",  method = RequestMethod.POST)
    @ResponseBody
    public Employee add(@ModelAttribute Employee employee) {

        employeService.save(employee);

        webSocketHandler.broadcastAdd(employee);

        return employee;
    }

    @RequestMapping(path = "/employee/update/{id}",  method = RequestMethod.POST)
    @ResponseBody
    public Employee update(@PathVariable Long id,  @RequestBody Employee employee) {

        Employee emp = employeService.findOne(id);

        webSocketHandler.broadcastUpdated(emp, employee);

        emp.setFirstName(employee.getFirstName());
        emp.setLastName(employee.getLastName());
        emp.setEmail(employee.getEmail());

        employeService.save(emp);

        return emp;
    }
}