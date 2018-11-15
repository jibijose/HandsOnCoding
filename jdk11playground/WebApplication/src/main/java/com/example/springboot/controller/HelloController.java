package com.example.springboot.controller;

import com.test.string.Util;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

  @RequestMapping("/hello")
  public String sayHello() {
    return "Greetings from Spring Boot!";
  }

  @RequestMapping("/hellonew/{name}")
  public String sayHelloNew(@PathVariable String name) {
    return "Greetings from Spring Boot! " + Util.length(name);
  }

}