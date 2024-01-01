package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Track;
import com.jradams.melophobia.entity.backing.TrackType;
import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.ComposerRepository;
import com.jradams.melophobia.repository.IsrcRepository;
import com.jradams.melophobia.repository.IswcRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
import com.jradams.melophobia.repository.TrackRepository;
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
@RequestMapping("/track")
public class TrackController {

    private static final String ACTION = "action";
    private static final String TRACK = "track";
    private static final String TRACK_FORM = "track/form";
    private static final String REDIRECT_HOME = "redirect:/track/";

    private final ArtistRepository artistRepo;
    private final ComposerRepository composerRepo;
    private final IsrcRepository isrcRepo;
    private final IswcRepository iswcRepo;
    private final ReleaseRepository releaseRepo;
    private final TrackRepository trackRepo;

    @Autowired
    TrackController(ArtistRepository artistRepo, ComposerRepository composerRepo, IsrcRepository isrcRepo,
                    IswcRepository iswcRepo, ReleaseRepository releaseRepo, TrackRepository trackRepo) {
        this.artistRepo = artistRepo;
        this.composerRepo = composerRepo;
        this.isrcRepo = isrcRepo;
        this.iswcRepo = iswcRepo;
        this.releaseRepo = releaseRepo;
        this.trackRepo = trackRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showTrackList(Model model) {
        model.addAttribute("tracks", trackRepo.findAllByOrderByTitleAsc());

        return "track/index";
    }

    @GetMapping("/{id}")
    public String showTrackDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Track> track = trackRepo.findById(id);

        track.ifPresent(v -> model.addAttribute(TRACK, v));

        return "track/detail";
    }

    @GetMapping("/add")
    public String showTrackForm(Model model) {
        populateTrackForm(model);
        model.addAttribute(TRACK, new Track());
        model.addAttribute(ACTION, "New");

        return TRACK_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showTrackForm(@PathVariable(value = "id") long id, Model model) {
        populateTrackForm(model);

        Optional<Track> track = trackRepo.findById(id);
        track.ifPresent(v -> model.addAttribute(TRACK, v));

        model.addAttribute(ACTION, "Edit");

        return TRACK_FORM;
    }

    @PostMapping("/add")
    public String saveTrack(@Valid Track track, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateTrackForm(model);
            model.addAttribute(ACTION, "New");

            return TRACK_FORM;
        }

        trackRepo.save(track);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveTrack(@PathVariable("id") long id, @Valid Track track, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            populateTrackForm(model);
            model.addAttribute(ACTION, "Edit");

            return TRACK_FORM;
        }

        track.setTrackId(id);
        trackRepo.save(track);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteTrack(@PathVariable("id") long id) {
        Track track = trackRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Track ID: %s", id)));

        trackRepo.delete(track);

        return REDIRECT_HOME;
    }

    private void populateTrackForm(Model model) {
        model.addAttribute("artists", artistRepo.findAllByOrderByNameAsc());
        model.addAttribute("composers", composerRepo.findAllByOrderByNameAsc());
        model.addAttribute("isrcs", isrcRepo.findAllByOrderByIsrcCodeAsc());
        model.addAttribute("iswcs", iswcRepo.findAllByOrderByIswcCodeAsc());
        model.addAttribute("releases", releaseRepo.findAllByOrderByTitleAsc());
        model.addAttribute("trackTypes", TrackType.values());
    }
}
