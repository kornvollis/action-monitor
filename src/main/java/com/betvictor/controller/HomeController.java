package com.betvictor.controller;

import com.betvictor.data.Employe;
import com.betvictor.data.EmployeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    EmployeService employeService;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {

        List<Employe> employes = employeService.getAll(null).getContent();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< employes.size(); i++) {
            sb.append(employes.get(i).getFirstName());
            sb.append("/n");
        }

        model.put("employes", employes);

        return "home";
    }
}