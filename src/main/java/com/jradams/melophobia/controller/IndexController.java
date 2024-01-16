package com.jradams.melophobia.controller;

import com.jradams.melophobia.repository.ArtistRepository;
import com.jradams.melophobia.repository.CollectionDigitalItemRepository;
import com.jradams.melophobia.repository.CollectionPhysicalItemRepository;
import com.jradams.melophobia.repository.ComposerRepository;
import com.jradams.melophobia.repository.CountryRepository;
import com.jradams.melophobia.repository.GenreRepository;
import com.jradams.melophobia.repository.IssueRepository;
import com.jradams.melophobia.repository.LabelRepository;
import com.jradams.melophobia.repository.LanguageRepository;
import com.jradams.melophobia.repository.LocationRepository;
import com.jradams.melophobia.repository.MediaRepository;
import com.jradams.melophobia.repository.ProducerRepository;
import com.jradams.melophobia.repository.RegionRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
import com.jradams.melophobia.repository.SeriesRepository;
import com.jradams.melophobia.repository.TrackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class IndexController {

    private ArtistRepository artistRepo;
    private CollectionDigitalItemRepository collectionDigitalItemRepo;
    private CollectionPhysicalItemRepository collectionPhysicalItemRepo;
    private ComposerRepository composerRepo;
    private CountryRepository countryRepo;
    private GenreRepository genreRepo;
    private IssueRepository issueRepo;
    private LabelRepository labelRepo;
    private LanguageRepository languageRepo;
    private LocationRepository locationRepo;
    private MediaRepository mediaRepo;
    private ProducerRepository producerRepo;
    private RegionRepository regionRepo;
    private ReleaseRepository releaseRepo;
    private SeriesRepository seriesRepo;
    private TrackRepository trackRepo;

    @Autowired
    IndexController(ArtistRepository artistRepo, CollectionDigitalItemRepository collectionDigitalItemRepo,
                    CollectionPhysicalItemRepository collectionPhysicalItemRepo, ComposerRepository composerRepo,
                    CountryRepository countryRepo, GenreRepository genreRepo, IssueRepository issueRepo,
                    LabelRepository labelRepo, LanguageRepository languageRepo, LocationRepository locationRepo,
                    MediaRepository mediaRepo, ProducerRepository producerRepo, RegionRepository regionRepo,
                    ReleaseRepository releaseRepo, SeriesRepository seriesRepo, TrackRepository trackRepo) {
        this.artistRepo = artistRepo;
        this.collectionDigitalItemRepo = collectionDigitalItemRepo;
        this.collectionPhysicalItemRepo = collectionPhysicalItemRepo;
        this.composerRepo = composerRepo;
        this.countryRepo = countryRepo;
        this.genreRepo = genreRepo;
        this.issueRepo = issueRepo;
        this.labelRepo = labelRepo;
        this.languageRepo = languageRepo;
        this.locationRepo = locationRepo;
        this.mediaRepo = mediaRepo;
        this.producerRepo = producerRepo;
        this.regionRepo = regionRepo;
        this.releaseRepo = releaseRepo;
        this.seriesRepo = seriesRepo;
        this.trackRepo = trackRepo;
    }

    @GetMapping
    public String showDashboard(Model model) {
        populateDashboardModel(model);

        return "index";
    }

    private void populateDashboardModel(Model model) {
        model.addAttribute("artistTotal", artistRepo.count());
        model.addAttribute("composerTotal", composerRepo.count());
        model.addAttribute("countryTotal", countryRepo.count());
        model.addAttribute("digitalCollectionItemTotal", collectionDigitalItemRepo.count());
        model.addAttribute("genreTotal", genreRepo.count());
        model.addAttribute("issueTotal", issueRepo.count());
        model.addAttribute("labelTotal", labelRepo.count());
        model.addAttribute("languageTotal", languageRepo.count());
        model.addAttribute("locationTotal", locationRepo.count());
        model.addAttribute("mediaTotal", mediaRepo.count());
        model.addAttribute("physicalCollectionItemTotal", collectionPhysicalItemRepo.count());
        model.addAttribute("producerTotal", producerRepo.count());
        model.addAttribute("regionTotal", regionRepo.count());
        model.addAttribute("releaseTotal", releaseRepo.count());
        model.addAttribute("seriesTotal", seriesRepo.count());
        model.addAttribute("trackTotal", trackRepo.count());
    }
}
