package com.betvictor.controller;

import com.betvictor.data.Employee;
import com.betvictor.data.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    private static Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    EmployeService employeService;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {

        logger.info("Home controller");

        List<Employee> employees = employeService.getAll(null).getContent();
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< employees.size(); i++) {
            sb.append(employees.get(i).getFirstName());
            sb.append("/n");
        }

        model.put("employes", employees);
        return "home";
    }
}