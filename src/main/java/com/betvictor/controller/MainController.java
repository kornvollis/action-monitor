package com.betvictor.controller;

import com.betvictor.data.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    EmployeeService employeeService;

    @Value("${info.version}")
    protected String appVersion;

    @RequestMapping("/action-monitor")
    public String showActionMonitor(Map<String, Object> model) {
        logger.info("Action monitor is loaded");
        return "action_monitor";
    }

    @RequestMapping("/isWorking")
    public String isWorking() {
        logger.info("Is working is loaded");
        return "status";
    }

    @RequestMapping("/version")
    public String version(Map<String, Object> model) {
        logger.info("Version is loaded");
        model.put("version", appVersion);

        return "version";
    }
}