package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Media;
import com.jradams.melophobia.repository.MediaRepository;
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
@RequestMapping("/media")
public class MediaController {

    private static final String ACTION = "action";
    private static final String LOCATION_FORM = "media/form";
    private static final String REDIRECT_HOME = "redirect:/media/";

    private final MediaRepository mediaRepo;

    @Autowired
    MediaController(MediaRepository mediaRepo) {
        this.mediaRepo = mediaRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showMediaList(Model model) {
        model.addAttribute("mediaList", mediaRepo.findAllByOrderByNameAsc());

        return "media/index";
    }

    @GetMapping("/add")
    public String showMediaForm(Model model) {
        model.addAttribute("media", new Media());
        model.addAttribute(ACTION, "New");

        return LOCATION_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showMediaForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Media> media = mediaRepo.findById(id);
        media.ifPresent(v -> model.addAttribute("media", v));

        model.addAttribute(ACTION, "Edit");

        return LOCATION_FORM;
    }

    @PostMapping("/add")
    public String saveMedia(@Valid Media media, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");

            return LOCATION_FORM;
        }

        mediaRepo.save(media);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveMedia(@PathVariable("id") long id, @Valid Media media, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");

            return LOCATION_FORM;
        }

        media.setMediaId(id);
        mediaRepo.save(media);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteMedia(@PathVariable("id") long id) {
        Media media = mediaRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Media ID: %s", id)));

        mediaRepo.delete(media);

        return REDIRECT_HOME;
    }
}
