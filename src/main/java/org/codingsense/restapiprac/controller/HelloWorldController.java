package org.codingsense.restapiprac.controller;

import org.codingsense.restapiprac.model.HelloWorldEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello-world")
    public String helloWorld() {
        return "Hello World!";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldEntity helloWorldBean(){
        return new HelloWorldEntity("Welcome, Hello World Bean!!!");
    }

    @GetMapping("/hello-world/path-variable/{name}")
    public HelloWorldEntity helloWorldPathVariable(@PathVariable String name){
        return new HelloWorldEntity(String.format("Hello World, %s!", name));
    }

    @GetMapping("/hello-world/internationalization")
    public String helloWorldInternationalization(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("good.morning.message",null,"Default message", locale);
    }
}
