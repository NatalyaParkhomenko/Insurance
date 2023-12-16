package com.insurance.controller;

import com.insurance.request.TransactionReq;
import com.insurance.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/transaction")
public class TransactionController {
    private final TransactionService service;

    @Autowired
    public TransactionController(TransactionService service) {
        this.service = service;
    }

    @GetMapping("/getAllByCardId")
    public String getAllByCardId(@RequestParam long cardId, Model model) {
        model.addAttribute("transactions", service.findAllByCardId(cardId));
        model.addAttribute("cardId", cardId);
        return "/transaction/transaction-list";
    }

    @GetMapping("/showFormForAdd")
    public String showFormForAdd(@RequestParam long cardId, Model model) {
        TransactionReq request = new TransactionReq();
        model.addAttribute("request", request);
        model.addAttribute("cardId", cardId);
        return "/transaction/transaction-form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute TransactionReq request, @RequestParam long cardId) {
        service.save(request);
        return "redirect:/transaction/getAllByCardId?cardId=" + cardId;
    }

    @GetMapping("/deleteById")
    public String deleteById(@RequestParam long id, @RequestParam long cardId) {
        service.deleteById(id);
        return "redirect:/transaction/getAllByCardId?cardId=" + cardId;

    }
}



