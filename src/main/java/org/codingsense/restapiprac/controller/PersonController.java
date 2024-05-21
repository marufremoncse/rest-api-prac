package org.codingsense.restapiprac.controller;

import org.codingsense.restapiprac.model.Name;
import org.codingsense.restapiprac.model.Person;
import org.codingsense.restapiprac.model.Person2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonController {
    @GetMapping("api/v1/person")
    public Person getFirstVersionOfPerson(){
        return new Person("Ahmad Muhammad");
    }
    @GetMapping("api/v2/person")
    public Person2 getSecondVersionOfPerson(){
        return new Person2(new Name("Ahmad", "Muhammad"));
    }
    @GetMapping(path = "api/person", params = "version=1")
    public Person getFirstVersionOfPersonRequestParam(){
        return new Person("Ahmad Muhammad");
    }
    @GetMapping(path = "api/person", params = "version=2")
    public Person2 getSecondVersionOfPersonRequestParam(){
        return new Person2(new Name("Ahmad", "Muhammad"));
    }
    @GetMapping(path = "api/person/header", headers = "X-API-VERSION=1")
    public Person getFirstVersionOfPersonRequestHeader(){
        return new Person("Ahmad Muhammad");
    }
    @GetMapping(path = "api/person/header", headers = "X-API-VERSION=2")
    public Person2 getSecondVersionOfPersonRequestHeader(){
        return new Person2(new Name("Ahmad", "Muhammad"));
    }
    @GetMapping(path = "api/person/accept", produces = "application/vnd.company.api-v1+json")
    public Person getFirstVersionOfPersonAcceptHeader(){
        return new Person("Ahmad Muhammad");
    }
    @GetMapping(path = "api/person/accept", produces = "application/vnd.company.api-v2+json")
    public Person2 getSecondVersionOfPersonAcceptHeader(){
        return new Person2(new Name("Ahmad", "Muhammad"));
    }
}
