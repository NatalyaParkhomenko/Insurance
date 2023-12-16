package com.insurance.controller;

import com.insurance.request.CustomerReq;
import com.insurance.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class CustomerController {
    private final CustomerService service;

    @Autowired
    public CustomerController(CustomerService service) {
        this.service = service;
    }

    @GetMapping("/getAllByInsuranceId")
    public String getAllByInsuranceId(@RequestParam long insuranceId, Model model) {
        model.addAttribute("customers", service.findAllByInsuranceId(insuranceId));
        model.addAttribute("insuranceId", insuranceId);
        return "/customer/customer-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam long insuranceId, Model model) {
        CustomerReq request = new CustomerReq();
        model.addAttribute("request", request);
        model.addAttribute("insuranceId", insuranceId);
        return "/customer/customer-form";

    }

    @PostMapping("/save")
    public String save(@ModelAttribute CustomerReq request, @RequestParam long insuranceId) {
        service.save(request);
        return "redirect:/customer/getAllByInsuranceId?insuranceId=" + insuranceId;
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam long id, @RequestParam long insuranceId) {
        service.deleteById(id);
        return "redirect:/customer/getAllByInsuranceId?insuranceId=" + insuranceId;
    }
}
