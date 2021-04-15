package com.familyapp.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class xController {


    @GetMapping("/events/all")
    public String eventsAllShow(){
        return "events";
    }

    @GetMapping("/events/add")
    public String eventsAddShow(){
        return "events-add";
    }

}
