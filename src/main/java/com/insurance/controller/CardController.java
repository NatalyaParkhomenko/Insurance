package com.insurance.controller;

import com.insurance.request.CardReq;
import com.insurance.service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/card")
public class CardController {
    private final CardService service;

    @Autowired
    public CardController(CardService service) {
        this.service = service;
    }


    @GetMapping("/getAllByCustomerId")
    public String getAllByCustomerId(@RequestParam long customerId, Model model) {
        model.addAttribute("cards", service.findAllByCustomerId(customerId));
        model.addAttribute("customerId", customerId);
        return "/card/card-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormFodAdd(@RequestParam long customerId, Model model) {
        CardReq request = new CardReq();
        model.addAttribute("request", request);
        model.addAttribute("customerId", customerId);
        return "/card/card-form";
    }


    @PostMapping("/save")
    public String save(@ModelAttribute CardReq request, @RequestParam long customerId) {
        service.save(request);
        return "redirect:/card/getAllByCustomerId?customerId=" + customerId;
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam long id, @RequestParam long customerId) {
        service.deleteById(id);
        return "redirect:/card/getAllByCustomerId?customerId=" + customerId;

    }
}
