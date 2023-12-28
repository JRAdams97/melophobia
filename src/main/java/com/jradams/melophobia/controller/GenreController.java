package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Genre;
import com.jradams.melophobia.repository.GenreRepository;
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
@RequestMapping("/genre")
public class GenreController {

    private static final String ACTION = "action";
    private static final String GENRE_FORM = "genre/form";
    private static final String GENRES = "genres";
    private static final String REDIRECT_HOME = "redirect:/genre/";

    private final GenreRepository genreRepo;

    @Autowired
    GenreController(GenreRepository genreRepo) {
        this.genreRepo = genreRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showGenreList(Model model) {
        model.addAttribute(GENRES, genreRepo.findAllByOrderByNameAsc());

        return "genre/index";
    }

    @GetMapping("/add")
    public String showGenreForm(Model model) {
        model.addAttribute(GENRES, genreRepo.findAllByOrderByNameAsc());
        model.addAttribute("genre", new Genre());
        model.addAttribute(ACTION, "New");

        return GENRE_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showGenreForm(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute(GENRES, genreRepo.findAllByOrderByNameAsc());

        Optional<Genre> genre = genreRepo.findById(id);
        genre.ifPresent(v -> model.addAttribute("genre", v));

        model.addAttribute(ACTION, "Edit");

        return GENRE_FORM;
    }

    @PostMapping("/add")
    public String saveGenre(@Valid Genre genre, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");
            model.addAttribute(GENRES, genreRepo.findAllByOrderByNameAsc());

            return GENRE_FORM;
        }

        genreRepo.save(genre);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveGenre(@PathVariable("id") long id, @Valid Genre genre, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");
            model.addAttribute(GENRES, genreRepo.findAllByOrderByNameAsc());

            return GENRE_FORM;
        }

        genre.setGenreId(id);
        genreRepo.save(genre);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") long id) {
        Genre genre = genreRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Genre ID: %s", id)));

        genreRepo.delete(genre);

        return REDIRECT_HOME;
    }
}
