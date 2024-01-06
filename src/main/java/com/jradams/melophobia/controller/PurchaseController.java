package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Purchase;
import com.jradams.melophobia.entity.backing.Currency;
import com.jradams.melophobia.repository.PurchaseRepository;
import com.jradams.melophobia.repository.VendorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/purchase")
public class PurchaseController {

    private static final String ACTION = "action";
    private static final String PURCHASE_FORM = "purchase/form";
    private static final String REDIRECT_HOME = "redirect:/purchase/";
    private static final String VENDORS = "vendors";

    private final PurchaseRepository purchaseRepo;
    private final VendorRepository vendorRepo;

    @Autowired
    PurchaseController(PurchaseRepository purchaseRepo, VendorRepository vendorRepo) {
        this.purchaseRepo = purchaseRepo;
        this.vendorRepo = vendorRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showPurchaseList(Model model) {
        model.addAttribute("purchases", purchaseRepo.findAllByOrderByPriceAsc());

        return "purchase/index";
    }

    @GetMapping("/add")
    public String showPurchaseForm(Model model) {
        model.addAttribute("purchase", new Purchase());
        populatePurchaseForm(model);
        model.addAttribute(ACTION, "New");

        return PURCHASE_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showPurchaseForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Purchase> purchase = purchaseRepo.findById(id);
        purchase.ifPresent(v -> model.addAttribute("purchase", v));

        populatePurchaseForm(model);
        model.addAttribute(ACTION, "Edit");

        return PURCHASE_FORM;
    }

    @PostMapping("/add")
    public String savePurchase(@Valid Purchase purchase, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populatePurchaseForm(model);
            model.addAttribute(ACTION, "New");

            return PURCHASE_FORM;
        }

        purchaseRepo.save(purchase);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String savePurchase(@PathVariable("id") long id, @Valid Purchase purchase, BindingResult bindResult,
                               Model model) {
        if (bindResult.hasErrors()) {
            populatePurchaseForm(model);
            model.addAttribute(ACTION, "Edit");

            return PURCHASE_FORM;
        }

        purchase.setPurchaseId(id);
        purchaseRepo.save(purchase);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deletePurchase(@PathVariable("id") long id) {
        Purchase purchase = purchaseRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Purchase ID: %s", id)));

        purchaseRepo.delete(purchase);

        return REDIRECT_HOME;
    }

    private void populatePurchaseForm(Model model) {
        model.addAttribute("currencies", Currency.values());
        model.addAttribute("vendors", vendorRepo.findAllByOrderByNameAsc());
    }
}
