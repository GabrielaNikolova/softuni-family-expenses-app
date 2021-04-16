package com.familyapp.web;

import com.familyapp.models.bindingModels.EventAddBindModel;
import com.familyapp.models.serviceModels.EventAddServModel;
import com.familyapp.services.EventService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping("/events")
public class EventController {

    private final ModelMapper modelMapper;
    private final EventService eventService;


    public EventController(ModelMapper modelMapper, EventService eventService) {
        this.modelMapper = modelMapper;
        this.eventService = eventService;
    }

    @ModelAttribute("eventAddBindModel")
    public EventAddBindModel createBindingModel() {
        return new EventAddBindModel();
    }


    @GetMapping("/add")
    public String eventsAddShow() {
        return "events-add";
    }

    @GetMapping("/all")
    public String eventsAll(Model model) {

        model.addAttribute("familyEvents", eventService.getAllByFamily());
        return "events";
    }


    @PostMapping("/add")
    public String addEvent(@Valid EventAddBindModel eventAddBindModel,
                           BindingResult bindingResult,
                           RedirectAttributes redirectAttributes) {


        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("eventAddBindModel", eventAddBindModel);
            redirectAttributes.addFlashAttribute("org.springframework.validation.BindingResult.eventAddBindModel", bindingResult);

            return "redirect:add";
        }

        EventAddServModel eventAddServiceModel = modelMapper
                .map(eventAddBindModel, EventAddServModel.class);

        eventService.addEvent(eventAddServiceModel);

        return "redirect:/events/all";
    }


    @GetMapping("/details/{id}")
    public String expenseDetails(@PathVariable String id, Model model) {

        model.addAttribute("eventDetails", eventService.getEventById(id));
        return "events-details";
    }


    @GetMapping("/delete/{id}")
    public String deleteById(@PathVariable String id) {
        eventService.deleteEventById(id);
        return "redirect:/events/all";
    }


}
