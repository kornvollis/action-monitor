package com.betvictor.controller;

import com.betvictor.data.EmployeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
public class MainController {

    private static Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    EmployeService employeService;

    @Value("${info.version}")
    protected String appVersion;

    @RequestMapping("/action-monitor")
    public String home(Map<String, Object> model) {

        logger.info("Home controller");
        
        return "action_monitor";
    }

    @RequestMapping("/isWorking")
    public String isWorking() {
        return "status";
    }

    @RequestMapping("/version")
    public String version(Map<String, Object> model) {
        model.put("version", appVersion);

        return "version";
    }
}