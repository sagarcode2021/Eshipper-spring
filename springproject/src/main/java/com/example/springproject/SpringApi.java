package com.example.springproject;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.google.gson.Gson;

import org.springframework.core.env.Environment;


@RestController
public class SpringApi {
   
    @Autowired
	private Environment environment;




@GetMapping("/timestamp")
public Map<String,String> getCurrentTime() {

	HashMap<String, String> map=new HashMap<>();
	LocalDateTime localDateTime = LocalDateTime.now();
       map.put("timestamp",localDateTime.toString());
    return map;
}


@GetMapping("/euroToUsd")
public String getEuroToUsd() throws JsonProcessingException {

	
	String porfile=this.environment.getActiveProfiles()[0];
    String url="http://www.floatrates.com/daily/EUR.json";

	String result="";

    RestTemplate restTemplate=new RestTemplate();
	Object currencyPrices=(Object) restTemplate.getForObject(url, Object.class);

	String s=new Gson().toJson(currencyPrices);
   
   String[] jsonArray=s.split(",");
 
   String st="\"chf\":{\"code\":\"CHF\"";
   String str=null;


   if(Arrays.asList(jsonArray).contains(st)){

	str=jsonArray[Arrays.asList(jsonArray).indexOf(st)+4];

   }else{
	str="Rate Not found";
   }

      if(porfile.equals("dev")){
	 result="\"rate\": 1.1";
	  }else{
		result=str;
	  }

     
    return result;
}

}
