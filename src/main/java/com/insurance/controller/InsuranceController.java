package com.insurance.controller;

import com.insurance.entity.Insurance;
import com.insurance.request.InsuranceReq;
import com.insurance.service.InsuranceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/insurance")
public class InsuranceController {
    private final InsuranceService service;

    @Autowired
    public InsuranceController(InsuranceService service) {
        this.service = service;
    }

    @GetMapping("/list")
    public String listInsurance(Model model) {
        List<Insurance> insurances = service.findAll();
        model.addAttribute("insurances", insurances);
        return "/insurance/insurance-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(Model model) {
        Insurance insurance = new Insurance();
        model.addAttribute("insurance", insurance);
        return "/insurance/insurance-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute InsuranceReq request) {
        service.save(request);
        return "redirect:/insurance/list";
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam("insuranceId") long id) {
        service.deleteById(id);
        return "redirect:/insurance/list";
    }
}
