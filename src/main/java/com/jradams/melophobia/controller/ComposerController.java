package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Composer;
import com.jradams.melophobia.repository.ComposerRepository;
import com.jradams.melophobia.repository.LocationRepository;
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
@RequestMapping("/composer")
public class ComposerController {

    private static final String ACTION = "action";
    private static final String COMPOSER_FORM = "composer/form";
    private static final String LOCATIONS = "locations";
    private static final String REDIRECT_HOME = "redirect:/composer/";

    private final ComposerRepository composerRepo;
    private final LocationRepository locationRepo;

    @Autowired
    ComposerController(ComposerRepository composerRepo, LocationRepository locationRepo) {
        this.composerRepo = composerRepo;
        this.locationRepo = locationRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showComposerList(Model model) {
        model.addAttribute("composers", composerRepo.findAllByOrderByNameAsc());

        return "composer/index";
    }

    @GetMapping("/add")
    public String showComposerForm(Model model) {
        model.addAttribute("composer", new Composer());
        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
        model.addAttribute(ACTION, "New");

        return COMPOSER_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showComposerForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Composer> composer = composerRepo.findById(id);
        composer.ifPresent(v -> model.addAttribute("composer", v));

        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
        model.addAttribute(ACTION, "Edit");

        return COMPOSER_FORM;
    }

    @PostMapping("/add")
    public String saveComposer(@Valid Composer composer, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
            model.addAttribute(ACTION, "New");

            return COMPOSER_FORM;
        }

        composerRepo.save(composer);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveComposer(@PathVariable("id") long id, @Valid Composer composer, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
            model.addAttribute(ACTION, "Edit");

            return COMPOSER_FORM;
        }

        composer.setComposerId(id);
        composerRepo.save(composer);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteComposer(@PathVariable("id") long id) {
        Composer composer = composerRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Composer ID: %s", id)));

        composerRepo.delete(composer);

        return REDIRECT_HOME;
    }
}
