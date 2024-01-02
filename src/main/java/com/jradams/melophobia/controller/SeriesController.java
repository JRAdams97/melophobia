package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Series;
import com.jradams.melophobia.repository.CountryRepository;
import com.jradams.melophobia.repository.SeriesRepository;
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
@RequestMapping("/series")
public class SeriesController {

    private static final String ACTION = "action";
    private static final String SERIES_FORM = "series/form";
    private static final String COUNTRIES = "countries";
    private static final String REDIRECT_HOME = "redirect:/series/";

    private final CountryRepository countryRepo;
    private final SeriesRepository seriesRepo;

    @Autowired
    SeriesController(CountryRepository countryRepo, SeriesRepository seriesRepo) {
        this.countryRepo = countryRepo;
        this.seriesRepo = seriesRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showSeriesList(Model model) {
        model.addAttribute("seriesList", seriesRepo.findAllByOrderByNameAsc());

        return "series/index";
    }

    @GetMapping("/add")
    public String showSeriesForm(Model model) {
        model.addAttribute("series", new Series());
        model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
        model.addAttribute(ACTION, "New");

        return SERIES_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showSeriesForm(@PathVariable(value = "id") long id, Model model) {
        Optional<Series> series = seriesRepo.findById(id);
        series.ifPresent(v -> model.addAttribute("series", v));

        model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
        model.addAttribute(ACTION, "Edit");

        return SERIES_FORM;
    }

    @PostMapping("/add")
    public String saveSeries(@Valid Series series, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
            model.addAttribute(ACTION, "New");

            return SERIES_FORM;
        }

        seriesRepo.save(series);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveSeries(@PathVariable("id") long id, @Valid Series series, BindingResult bindResult,
                             Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(COUNTRIES, countryRepo.findAllByOrderByCountryNameAsc());
            model.addAttribute(ACTION, "Edit");

            return SERIES_FORM;
        }

        series.setSeriesId(id);
        seriesRepo.save(series);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteSeries(@PathVariable("id") long id) {
        Series series = seriesRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Series ID: %s", id)));

        seriesRepo.delete(series);

        return REDIRECT_HOME;
    }
}
