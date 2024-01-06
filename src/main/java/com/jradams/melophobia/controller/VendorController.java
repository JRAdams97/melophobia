package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Vendor;
import com.jradams.melophobia.repository.LocationRepository;
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
@RequestMapping("/vendor")
public class VendorController {

    private static final String ACTION = "action";
    private static final String REDIRECT_HOME = "redirect:/vendor/";
    private static final String VENDOR = "vendor";
    private static final String VENDOR_FORM = "vendor/form";

    private final LocationRepository locationRepo;
    private final PurchaseRepository purchaseRepo;
    private final VendorRepository vendorRepo;

    @Autowired
    VendorController(LocationRepository locationRepo, PurchaseRepository purchaseRepo, VendorRepository vendorRepo) {
        this.locationRepo = locationRepo;
        this.purchaseRepo = purchaseRepo;
        this.vendorRepo = vendorRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showVendorList(Model model) {
        model.addAttribute("vendors", vendorRepo.findAllByOrderByNameAsc());

        return "vendor/index";
    }

    @GetMapping("/{id}")
    public String showVendorDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Vendor> vendor = vendorRepo.findById(id);
        vendor.ifPresent(v -> model.addAttribute(VENDOR, v));

        return "vendor/detail";
    }

    @GetMapping("/add")
    public String showVendorForm(Model model) {
        model.addAttribute(VENDOR, new Vendor());
        populateVendorForm(model);
        model.addAttribute(ACTION, "New");

        return VENDOR_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showVendorForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Vendor> vendor = vendorRepo.findById(id);
        vendor.ifPresent(v -> model.addAttribute(VENDOR, v));

        populateVendorForm(model);
        model.addAttribute(ACTION, "Edit");

        return VENDOR_FORM;
    }

    @PostMapping("/add")
    public String saveVendor(@Valid Vendor vendor, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateVendorForm(model);
            model.addAttribute(ACTION, "New");

            return VENDOR_FORM;
        }

        vendorRepo.save(vendor);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveVendor(@PathVariable("id") long id, @Valid Vendor vendor, BindingResult bindResult,
                               Model model) {
        if (bindResult.hasErrors()) {
            populateVendorForm(model);
            model.addAttribute(ACTION, "Edit");

            return VENDOR_FORM;
        }

        vendor.setVendorId(id);
        vendorRepo.save(vendor);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteVendor(@PathVariable("id") long id) {
        Vendor vendor = vendorRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Vendor ID: %s", id)));

        vendorRepo.delete(vendor);

        return REDIRECT_HOME;
    }

    private void populateVendorForm(Model model) {
        model.addAttribute("locations", locationRepo.findAllByOrderByCityAsc());
        model.addAttribute("purchases", purchaseRepo.findAllByOrderByPriceAsc());
    }
}
