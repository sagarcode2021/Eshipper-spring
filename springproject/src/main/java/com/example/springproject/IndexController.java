package com.example.springproject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import ch.qos.logback.classic.Logger;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mobile.device.Device;

@Controller
public class IndexController {

    @Autowired
	private Environment environment;


	private static final Logger logger = (Logger) LoggerFactory.getLogger(SpringprojectApplication.class);

  
    
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String greeting(Device device) {
		
        String deviceType = "browser";
        String platform = "browser";
        String viewName = "index";
		
        if (device.isNormal()) {
            deviceType = "browser";
            System.out.println(deviceType);
        } else if (device.isMobile()) {
            deviceType = "mobile";
            viewName = "mobile/index";
        } else if (device.isTablet()) {
            deviceType = "tablet";
            viewName = "tablet/index";
        }
        
        platform = device.getDevicePlatform().name();
        
        if (platform.equalsIgnoreCase("UNKNOWN")) {
            platform = "browser";
        }
     	
        return viewName;
    }





}
