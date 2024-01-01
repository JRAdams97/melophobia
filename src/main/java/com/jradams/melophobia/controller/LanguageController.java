package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Language;
import com.jradams.melophobia.entity.backing.LanguageName;
import com.jradams.melophobia.repository.LanguageRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/language")
public class LanguageController {

    private static final String ACTION = "action";
    private static final String LANGUAGE_FORM = "language/form";
    private static final String LANGUAGE_NAMES = "languageNames";
    private static final String REDIRECT_HOME = "redirect:/language/";

    private final LanguageRepository languageRepo;

    @Autowired
    LanguageController(LanguageRepository languageRepo) {
        this.languageRepo = languageRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showLanguageList(Model model) {
        model.addAttribute("languages", languageRepo.findAllByOrderByNameAsc());

        return "language/index";
    }

    @GetMapping("/add")
    public String showLanguageForm(Model model) {
        model.addAttribute("language", new Language());
        populateLanguageNameList(model);
        model.addAttribute(LANGUAGE_NAMES, LanguageName.values());
        model.addAttribute(ACTION, "New");

        return LANGUAGE_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showLanguageForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Language> language = languageRepo.findById(id);
        language.ifPresent(v -> model.addAttribute("language", v));

        populateLanguageNameList(model);
        model.addAttribute(LANGUAGE_NAMES, LanguageName.values());
        model.addAttribute(ACTION, "Edit");

        return LANGUAGE_FORM;
    }

    @PostMapping("/add")
    public String saveLanguage(@Valid Language language, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateLanguageNameList(model);
            model.addAttribute(LANGUAGE_NAMES, LanguageName.values());
            model.addAttribute(ACTION, "New");

            return LANGUAGE_FORM;
        }

        languageRepo.save(language);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveLanguage(@PathVariable("id") long id, @Valid Language language, BindingResult bindResult,
                               Model model) {
        if (bindResult.hasErrors()) {
            populateLanguageNameList(model);
            model.addAttribute(LANGUAGE_NAMES, LanguageName.values());
            model.addAttribute(ACTION, "Edit");

            return LANGUAGE_FORM;
        }

        language.setLanguageId(id);
        languageRepo.save(language);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteLanguage(@PathVariable("id") long id) {
        Language language = languageRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Language ID: %s", id)));

        languageRepo.delete(language);

        return REDIRECT_HOME;
    }

    private void populateLanguageNameList(Model model) {
        List<Language> languages = languageRepo.findAllByOrderByNameAsc();
        model.addAttribute("languageNameList", languages.stream().map(Language::getName).toList());
    }
}
