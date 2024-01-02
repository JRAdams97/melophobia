package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Region;
import com.jradams.melophobia.repository.CountryRepository;
import com.jradams.melophobia.repository.RegionRepository;
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
@RequestMapping("/region")
public class RegionController {

    private static final String ACTION = "action";
    private static final String COUNTRIES = "countries";
    private static final String REGION_FORM = "region/form";
    private static final String REDIRECT_HOME = "redirect:/region/";

    private final CountryRepository countryRepo;
    private final RegionRepository regionRepo;

    @Autowired
    RegionController(CountryRepository countryRepo, RegionRepository regionRepo) {
        this.countryRepo = countryRepo;
        this.regionRepo = regionRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showRegionList(Model model) {
        model.addAttribute("regions", regionRepo.findAllByOrderByNameAsc());

        return "region/index";
    }

    @GetMapping("/add")
    public String showRegionForm(Model model) {
        model.addAttribute("region", new Region());
        model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
        model.addAttribute(ACTION, "New");

        return REGION_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showRegionForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Region> region = regionRepo.findById(id);
        region.ifPresent(v -> model.addAttribute("region", v));

        model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
        model.addAttribute(ACTION, "Edit");

        return REGION_FORM;
    }

    @PostMapping("/add")
    public String saveRegion(@Valid Region region, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
            model.addAttribute(ACTION, "New");

            return REGION_FORM;
        }

        regionRepo.save(region);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveRegion(@PathVariable("id") long id, @Valid Region region, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
            model.addAttribute(ACTION, "Edit");

            return REGION_FORM;
        }

        region.setRegionId(id);
        regionRepo.save(region);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteRegion(@PathVariable("id") long id) {
        Region region = regionRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Region ID: %s", id)));

        regionRepo.delete(region);

        return REDIRECT_HOME;
    }
}
