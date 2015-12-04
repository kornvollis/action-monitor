package com.betvictor.controller;

import com.betvictor.WebSocketHandler;
import com.betvictor.data.EmployeeService;
import com.betvictor.data.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeeController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    WebSocketHandler webSocketHandler;

    @RequestMapping(value = {"/", "/employee"})
    public String showEmployees(Map<String, Object> model) {
        logger.info("Employee page load");
        List<Employee> employees = employeeService.getAll(null).getContent();

        model.put("employees", employees);
        return "home";
    }

    @RequestMapping(path = "/employee/delete/{id}", method = RequestMethod.GET)
    @ResponseBody
    public String delete(@PathVariable Long id) {
        Employee emp = employeeService.findOne(id);
        if(emp != null) {
            logger.info("Employee deleted: " + emp.toString());
            employeeService.delete(id);

            webSocketHandler.broadcastDelete(emp);
        } else {
            logger.info("Invalid employee id: " + id);
        }
        return "deleted";
    }

    @RequestMapping(path = "/employee/add", method = RequestMethod.POST)
    @ResponseBody
    public Employee add(@ModelAttribute Employee employee) {
        if(employee != null) {
            logger.info("Employee added: " + employee.toString());
            employeeService.save(employee);

            webSocketHandler.broadcastAdd(employee);
        } else {
            logger.info("Invalid null employee");
        }

        return employee;
    }

    @RequestMapping(path = "/employee/update/{id}", method = RequestMethod.POST)
    @ResponseBody
    public Employee update(@PathVariable Long id, @RequestBody Employee newEmployee) {

        Employee employee = employeeService.findOne(id);
        if(employee != null) {
            employee.setFirstName(newEmployee.getFirstName());
            employee.setLastName(newEmployee.getLastName());
            employee.setEmail(newEmployee.getEmail());
            employeeService.save(employee);
            logger.info("Employee updated: " + employee.toString());
            webSocketHandler.broadcastUpdated(employee);
        } else {
            logger.info("Invalid employee id for update");
        }
        return employee;
    }
}