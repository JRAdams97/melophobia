package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Artist;
import com.jradams.melophobia.entity.Release;
import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/artist")
public class ArtistController {

    private final ArtistRepository artistRepository;

    @Autowired
    ArtistController(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
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


}
