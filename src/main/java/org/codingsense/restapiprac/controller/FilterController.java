package org.codingsense.restapiprac.controller;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.codingsense.restapiprac.model.SomeBean;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class FilterController {
    MappingJacksonValue mappingJacksonValue;
    SimpleBeanPropertyFilter filter;
    FilterProvider filterProvider;

    @GetMapping("filtering")
    public MappingJacksonValue getSomeBean(){
        SomeBean someBean = new SomeBean("value1", "value2", "value3");
        mappingJacksonValue = new MappingJacksonValue(someBean);
        filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1", "field2");
        filterProvider = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
    @GetMapping("filtering/all")
    public MappingJacksonValue getSomeBeans(){
        List<SomeBean> list = Arrays.asList(new SomeBean("value1", "value2", "value3"), new SomeBean("value4", "value5", "value6"));

        mappingJacksonValue = new MappingJacksonValue(list);
        filter = SimpleBeanPropertyFilter.filterOutAllExcept( "field2");
        filterProvider = new SimpleFilterProvider().addFilter("someBeanFilter", filter);
        mappingJacksonValue.setFilters(filterProvider);
        return mappingJacksonValue;
    }
}
