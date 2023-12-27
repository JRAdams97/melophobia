package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Artist;
import com.jradams.melophobia.entity.Release;
import com.jradams.melophobia.entity.backing.ArtistType;
import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.GenreRepository;
import com.jradams.melophobia.repository.LocationRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistRepository artistRepository;
    private final GenreRepository genreRepository;
    private final LocationRepository locationRepository;

    @Autowired
    ArtistController(ArtistRepository artistRepository, GenreRepository genreRepository,
                     LocationRepository locationRepository) {
        this.artistRepository = artistRepository;
        this.genreRepository = genreRepository;
        this.locationRepository = locationRepository;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showArtistList(Model model) {
        model.addAttribute("artists", artistRepository.findAllByOrderByNameAsc());

        return "artist/index";
    }

    @GetMapping("/{id}")
    public String showArtistDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Artist> artist = artistRepository.findById(id);

        artist.ifPresent(v -> model.addAttribute("artist", v));

        return "artist/detail";
    }

    @GetMapping("/add")
    public String showArtistForm(Model model) {
        populateArtistForm(model);

        model.addAttribute("artist", new Artist());
        model.addAttribute("action", "New");

        return "artist/form";
    }

    @GetMapping("/edit/{id}")
    public String showArtistForm(@PathVariable(value = "id") long id, Model model) {
        populateArtistForm(model);

        Optional<Artist> artist = artistRepository.findById(id);
        artist.ifPresent(v -> model.addAttribute("artist", v));

        model.addAttribute("action", "Edit");

        return "artist/form";
    }

    @PostMapping("/add")
    public String saveNewArtist(@Valid Artist artist, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            populateArtistForm(model);

            return "artist/form";
        }

        artistRepository.save(artist);

        return "redirect:/artist/";
    }

    @PostMapping("/edit/{id}")
    public String saveEditedArtist(@PathVariable("id") long id, @Valid Artist artist, BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            populateArtistForm(model);

            return "artist/form";
        }

        artist.setArtistId(id);
        artistRepository.save(artist);

        return "redirect:/artist/";
    }

    private void populateArtistForm(Model model) {
        model.addAttribute("genres", genreRepository.findAllByOrderByNameAsc());
        model.addAttribute("locations", locationRepository.findAllByOrderByCityAsc());
        model.addAttribute("artistTypes", ArtistType.values());
    }
}
