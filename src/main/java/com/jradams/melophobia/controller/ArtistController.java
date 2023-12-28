package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Artist;
import com.jradams.melophobia.entity.backing.ArtistType;
import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.GenreRepository;
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
@RequestMapping("/artist")
public class ArtistController {

    private static final String ACTION = "action";
    private static final String ARTIST = "artist";
    private static final String ARTIST_FORM = "artist/form";
    private static final String REDIRECT_HOME = "redirect:/artist/";

    private final ArtistRepository artistRepo;
    private final GenreRepository genreRepo;
    private final LocationRepository locationRepo;

    @Autowired
    ArtistController(ArtistRepository artistRepo, GenreRepository genreRepo, LocationRepository locationRepo) {
        this.artistRepo = artistRepo;
        this.genreRepo = genreRepo;
        this.locationRepo = locationRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showArtistList(Model model) {
        model.addAttribute("artists", artistRepo.findAllByOrderByNameAsc());

        return "artist/index";
    }

    @GetMapping("/{id}")
    public String showArtistDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Artist> artist = artistRepo.findById(id);

        artist.ifPresent(v -> model.addAttribute(ARTIST, v));

        return "artist/detail";
    }

    @GetMapping("/add")
    public String showArtistForm(Model model) {
        populateArtistForm(model);

        model.addAttribute(ARTIST, new Artist());
        model.addAttribute(ACTION, "New");

        return ARTIST_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showArtistForm(@PathVariable(value = "id") long id, Model model) {
        populateArtistForm(model);

        Optional<Artist> artist = artistRepo.findById(id);
        artist.ifPresent(v -> model.addAttribute(ARTIST, v));

        model.addAttribute(ACTION, "Edit");

        return ARTIST_FORM;
    }

    @PostMapping("/add")
    public String saveArtist(@Valid Artist artist, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");
            populateArtistForm(model);

            return ARTIST_FORM;
        }

        artistRepo.save(artist);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveArtist(@PathVariable("id") long id, @Valid Artist artist, BindingResult bindResult,
                             Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");
            populateArtistForm(model);

            return ARTIST_FORM;
        }

        artist.setArtistId(id);
        artistRepo.save(artist);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteArtist(@PathVariable("id") long id) {
        Artist artist = artistRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Artist ID: %s", id)));

        artistRepo.delete(artist);

        return REDIRECT_HOME;
    }

    private void populateArtistForm(Model model) {
        model.addAttribute("genres", genreRepo.findAllByOrderByNameAsc());
        model.addAttribute("locations", locationRepo.findAllByOrderByCityAsc());
        model.addAttribute("artistTypes", ArtistType.values());
    }
}
