package com.micropos.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.*;


//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
@SpringBootApplication
@EnableDiscoveryClient
//@RestController
public class GatewayApplication {

    @RequestMapping("/")
    public String home(){
        return "hello, gateway!";
    }
    public static void main(String[] args) {
        System.setProperty("spring.config.name","gateway");
        SpringApplication.run(GatewayApplication.class, args);
    }

}
