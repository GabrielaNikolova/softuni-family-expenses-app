package com.familyapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class xController {


    @GetMapping("/expenses/add")
    public String expensesAddShow(){
        return "expenses-add";
    }

    @GetMapping("/expenses/all")
    public String expensesAllShow(){
        return "expenses";
    }


    @GetMapping("/events/all")
    public String eventsAllShow(){
        return "events";
    }

    @GetMapping("/events/add")
    public String eventsAddShow(){
        return "events-add";
    }

}
