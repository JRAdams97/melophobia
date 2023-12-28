package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Label;
import com.jradams.melophobia.repository.LabelRepository;
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
@RequestMapping("/label")
public class LabelController {

    private static final String ACTION = "action";
    private static final String LABEL = "label";
    private static final String LABEL_FORM = "label/form";
    private static final String LABELS = "labels";
    private static final String LOCATIONS = "locations";
    private static final String REDIRECT_HOME = "redirect:/label/";

    private final LabelRepository labelRepo;
    private final LocationRepository locationRepo;

    @Autowired
    LabelController(LabelRepository labelRepo, LocationRepository locationRepo) {
        this.labelRepo = labelRepo;
        this.locationRepo = locationRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showLabelList(Model model) {
        model.addAttribute(LABELS, labelRepo.findAllByOrderByNameAsc());

        return "label/index";
    }

    @GetMapping("/{id}")
    public String showLabelDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Label> label = labelRepo.findById(id);

        label.ifPresent(v -> model.addAttribute(LABEL, v));

        return "label/detail";
    }

    @GetMapping("/add")
    public String showLabelForm(Model model) {
        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
        model.addAttribute(LABEL, new Label());
        model.addAttribute(ACTION, "New");

        return LABEL_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showLabelForm(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());

        Optional<Label> label = labelRepo.findById(id);
        label.ifPresent(v -> model.addAttribute(LABEL, v));

        model.addAttribute(ACTION, "Edit");

        return LABEL_FORM;
    }

    @PostMapping("/add")
    public String saveLabel(@Valid Label label, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());

            return LABEL_FORM;
        }

        labelRepo.save(label);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveLabel(@PathVariable("id") long id, @Valid Label label, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());

            return LABEL_FORM;
        }

        label.setLabelId(id);
        labelRepo.save(label);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteLabel(@PathVariable("id") long id) {
        Label label = labelRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Label ID: %s", id)));

        labelRepo.delete(label);

        return REDIRECT_HOME;
    }
}
