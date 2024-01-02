package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Country;
import com.jradams.melophobia.entity.backing.Continent;
import com.jradams.melophobia.repository.CountryRepository;
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
@RequestMapping("/country")
public class CountryController {

    private static final String ACTION = "action";
    private static final String CONTINENTS = "continents";
    private static final String COUNTRY_FORM = "country/form";
    private static final String REDIRECT_HOME = "redirect:/country/";

    private final CountryRepository countryRepo;

    @Autowired
    CountryController(CountryRepository countryRepo) {
        this.countryRepo = countryRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showCountryList(Model model) {
        model.addAttribute("countries", countryRepo.findAllByOrderByCountryNameAsc());

        return "country/index";
    }

    @GetMapping("/add")
    public String showCountryForm(Model model) {
        model.addAttribute("country", new Country());
        model.addAttribute(CONTINENTS, Continent.values());
        model.addAttribute(ACTION, "New");

        return COUNTRY_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showCountryForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Country> country = countryRepo.findById(id);
        country.ifPresent(v -> model.addAttribute("country", v));

        model.addAttribute(CONTINENTS, Continent.values());
        model.addAttribute(ACTION, "Edit");

        return COUNTRY_FORM;
    }

    @PostMapping("/add")
    public String saveCountry(@Valid Country country, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(CONTINENTS, Continent.values());
            model.addAttribute(ACTION, "New");

            return COUNTRY_FORM;
        }

        countryRepo.save(country);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveCountry(@PathVariable("id") long id, @Valid Country country, BindingResult bindResult,
                              Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(CONTINENTS, Continent.values());
            model.addAttribute(ACTION, "Edit");

            return COUNTRY_FORM;
        }

        country.setCountryId(id);
        countryRepo.save(country);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteCountry(@PathVariable("id") long id) {
        Country country = countryRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Country ID: %s", id)));

        countryRepo.delete(country);

        return REDIRECT_HOME;
    }
}
