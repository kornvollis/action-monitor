package com.betvictor.controller;

import com.betvictor.data.Employe;
import com.betvictor.data.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class EmployeController {

    @Autowired
    EmployeService employeService;

    @RequestMapping("/empoye/delete/{id}")
    public String home(Map<String, Object> model) {

        employeService.

        return "home";
    }
}