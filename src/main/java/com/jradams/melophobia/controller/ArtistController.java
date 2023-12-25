package com.jradams.melophobia.controller;

import com.jradams.melophobia.repository.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        model.addAttribute("artists", artistRepository.findAll());

        return "artist/index";
    }
}
