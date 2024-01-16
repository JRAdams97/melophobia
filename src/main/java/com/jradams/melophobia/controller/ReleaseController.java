package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Release;
import com.jradams.melophobia.entity.ReleaseType;
import com.jradams.melophobia.entity.backing.ReleaseTypeName;
import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.GenreRepository;
import com.jradams.melophobia.repository.LanguageRepository;
import com.jradams.melophobia.repository.ProducerRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
import com.jradams.melophobia.repository.ReleaseTypeRepository;
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

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/release")
public class ReleaseController {

    private static final String ACTION = "action";
    private static final String RELEASE = "release";
    private static final String RELEASE_FORM = "release/form";
    private static final String REDIRECT_HOME = "redirect:/release/";

    private final ArtistRepository artistRepo;
    private final GenreRepository genreRepo;
    private final LanguageRepository languageRepo;
    private final ProducerRepository producerRepo;
    private final ReleaseRepository releaseRepo;
    private final ReleaseTypeRepository releaseTypeRepo;
    private final SeriesRepository seriesRepo;

    @Autowired
    ReleaseController(ArtistRepository artistRepo, GenreRepository genreRepo, LanguageRepository languageRepo,
                      ProducerRepository producerRepo, ReleaseRepository releaseRepo,
                      ReleaseTypeRepository releaseTypeRepo, SeriesRepository seriesRepo) {
        this.artistRepo = artistRepo;
        this.genreRepo = genreRepo;
        this.languageRepo = languageRepo;
        this.producerRepo = producerRepo;
        this.releaseRepo = releaseRepo;
        this.releaseTypeRepo = releaseTypeRepo;
        this.seriesRepo = seriesRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showReleaseList(Model model) {
        model.addAttribute("releases", releaseRepo.findAllByOrderByReleaseDateAsc());

        return "release/index";
    }

    @GetMapping("/{id}")
    public String showReleaseDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Release> release = releaseRepo.findById(id);

        release.ifPresent(v -> model.addAttribute(RELEASE, v));

        return "release/detail";
    }

    @GetMapping("/add")
    public String showReleaseForm(Model model) {
        populateReleaseForm(model);
        model.addAttribute(RELEASE, new Release());
        model.addAttribute(ACTION, "New");

        return RELEASE_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showReleaseForm(@PathVariable(value = "id") long id, Model model) {
        populateReleaseForm(model);

        Optional<Release> release = releaseRepo.findById(id);
        release.ifPresent(v -> model.addAttribute(RELEASE, v));

        model.addAttribute(ACTION, "Edit");

        return RELEASE_FORM;
    }

    @PostMapping("/add")
    public String saveRelease(@Valid Release release, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateReleaseForm(model);
            model.addAttribute(ACTION, "New");

            return RELEASE_FORM;
        }

        releaseRepo.save(release);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveRelease(@PathVariable("id") long id, @Valid Release release, BindingResult bindResult,
                              Model model) {
        if (bindResult.hasErrors()) {
            populateReleaseForm(model);
            model.addAttribute(ACTION, "Edit");

            return RELEASE_FORM;
        }

        release.setReleaseId(id);
        releaseRepo.save(release);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteRelease(@PathVariable("id") long id) {
        Release release = releaseRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Release ID: %s", id)));

        releaseRepo.delete(release);

        return REDIRECT_HOME;
    }

    // ReleaseType mappings
    @GetMapping("/type/")
    public String showReleaseTypeList(Model model) {
        model.addAttribute("releaseTypes", releaseTypeRepo.findAllByOrderByNameAsc());

        return "release/type/index";
    }

    @GetMapping("/type/add")
    public String showReleaseTypeForm(Model model) {
        populateReleaseTypeList(model);
        model.addAttribute("releaseType", new ReleaseType());
        model.addAttribute("releaseTypeNames", ReleaseTypeName.values());

        return "release/type/form";
    }

    @PostMapping("/type/add")
    public String saveReleaseType(@Valid ReleaseType releaseType, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateReleaseTypeList(model);
            model.addAttribute("releaseTypeNames", ReleaseTypeName.values());

            return "release/type/form";
        }

        releaseTypeRepo.save(releaseType);

        return REDIRECT_HOME;
    }

    @GetMapping("/type/delete/{id}")
    public String deleteReleaseType(@PathVariable("id") long id) {
        ReleaseType releaseType = releaseTypeRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Release Type ID: %s", id)));

        releaseTypeRepo.delete(releaseType);

        return REDIRECT_HOME;
    }

    private void populateReleaseForm(Model model) {
        model.addAttribute("artists", artistRepo.findAllByOrderByNameAsc());
        model.addAttribute("genres", genreRepo.findAllByOrderByNameAsc());
        model.addAttribute("languages", languageRepo.findAllByOrderByNameAsc());
        model.addAttribute("producers", producerRepo.findAllByOrderByNameAsc());
        model.addAttribute("releaseTypes", releaseTypeRepo.findAllByOrderByNameAsc());
        model.addAttribute("seriesList", seriesRepo.findAllByOrderByNameAsc());
    }

    private void populateReleaseTypeList(Model model) {
        List<ReleaseType> releaseTypes = releaseTypeRepo.findAllByOrderByNameAsc();
        model.addAttribute("releaseTypeList", releaseTypes.stream().map(ReleaseType::getName).toList());
    }
}
