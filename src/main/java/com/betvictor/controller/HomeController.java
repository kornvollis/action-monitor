package com.betvictor.controller;

import com.betvictor.data.Product;
import com.betvictor.data.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Map;

@Controller
public class HomeController {

    @Autowired
    ProductService productService;

    @RequestMapping("/")
    public String home(Map<String, Object> model) {

        List<Product> products = productService.getAllProducts(null).getContent();

        StringBuilder sb = new StringBuilder();

        for(int i = 0; i< products.size(); i++) {
            sb.append(products.get(i).getName());
            sb.append("/n");
        }

        model.put("products", products);

        return "home";
    }
}