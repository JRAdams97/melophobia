package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Location;
import com.jradams.melophobia.repository.LocationRepository;
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
@RequestMapping("/location")
public class LocationController {

    private static final String ACTION = "action";
    private static final String LOCATION_FORM = "location/form";
    private static final String REDIRECT_HOME = "redirect:/location/";
    private static final String REGIONS = "regions";

    private final LocationRepository locationRepo;
    private final RegionRepository regionRepo;

    @Autowired
    LocationController(LocationRepository locationRepo, RegionRepository regionRepo) {
        this.locationRepo = locationRepo;
        this.regionRepo = regionRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showLocationList(Model model) {
        model.addAttribute("locations", locationRepo.findAllByOrderByCityAsc());

        return "location/index";
    }

    @GetMapping("/add")
    public String showLocationForm(Model model) {
        model.addAttribute("location", new Location());
        model.addAttribute(REGIONS, regionRepo.findAllByOrderByNameAsc());
        model.addAttribute(ACTION, "New");

        return LOCATION_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showLocationForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Location> location = locationRepo.findById(id);
        location.ifPresent(v -> model.addAttribute("location", v));

        model.addAttribute(REGIONS, regionRepo.findAllByOrderByNameAsc());
        model.addAttribute(ACTION, "Edit");

        return LOCATION_FORM;
    }

    @PostMapping("/add")
    public String saveLocation(@Valid Location location, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(REGIONS, regionRepo.findAllByOrderByNameAsc());
            model.addAttribute(ACTION, "New");

            return LOCATION_FORM;
        }

        locationRepo.save(location);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveLocation(@PathVariable("id") long id, @Valid Location location, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(REGIONS, regionRepo.findAllByOrderByNameAsc());
            model.addAttribute(ACTION, "Edit");

            return LOCATION_FORM;
        }

        location.setLocationId(id);
        locationRepo.save(location);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteLocation(@PathVariable("id") long id) {
        Location location = locationRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Location ID: %s", id)));

        locationRepo.delete(location);

        return REDIRECT_HOME;
    }
}
