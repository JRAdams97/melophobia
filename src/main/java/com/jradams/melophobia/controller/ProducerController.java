package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Producer;
import com.jradams.melophobia.repository.ProducerRepository;
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
@RequestMapping("/producer")
public class ProducerController {

    private static final String ACTION = "action";
    private static final String PRODUCER_FORM = "producer/form";
    private static final String LOCATIONS = "locations";
    private static final String REDIRECT_HOME = "redirect:/producer/";

    private final ProducerRepository producerRepo;
    private final LocationRepository locationRepo;

    @Autowired
    ProducerController(ProducerRepository producerRepo, LocationRepository locationRepo) {
        this.producerRepo = producerRepo;
        this.locationRepo = locationRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showProducerList(Model model) {
        model.addAttribute("producers", producerRepo.findAllByOrderByNameAsc());

        return "producer/index";
    }

    @GetMapping("/add")
    public String showProducerForm(Model model) {
        model.addAttribute("producer", new Producer());
        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
        model.addAttribute(ACTION, "New");

        return PRODUCER_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showProducerForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Producer> producer = producerRepo.findById(id);
        producer.ifPresent(v -> model.addAttribute("producer", v));

        model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
        model.addAttribute(ACTION, "Edit");

        return PRODUCER_FORM;
    }

    @PostMapping("/add")
    public String saveProducer(@Valid Producer producer, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
            model.addAttribute(ACTION, "New");

            return PRODUCER_FORM;
        }

        producerRepo.save(producer);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveProducer(@PathVariable("id") long id, @Valid Producer producer, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(LOCATIONS, locationRepo.findAllByOrderByCityAsc());
            model.addAttribute(ACTION, "Edit");

            return PRODUCER_FORM;
        }

        producer.setProducerId(id);
        producerRepo.save(producer);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteProducer(@PathVariable("id") long id) {
        Producer producer = producerRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Producer ID: %s", id)));

        producerRepo.delete(producer);

        return REDIRECT_HOME;
    }
}
