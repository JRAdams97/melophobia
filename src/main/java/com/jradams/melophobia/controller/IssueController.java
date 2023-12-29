package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.Issue;
import com.jradams.melophobia.repository.CountryRepository;
import com.jradams.melophobia.repository.IssueRepository;
import com.jradams.melophobia.repository.LabelRepository;
import com.jradams.melophobia.repository.MediaRepository;
import com.jradams.melophobia.repository.ReleaseRepository;
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
@RequestMapping("/issue")
public class IssueController {

    private static final String ACTION = "action";
    private static final String ISSUE = "issue";
    private static final String ISSUE_FORM = "issue/form";
    private static final String REDIRECT_HOME = "redirect:/issue/";

    private final CountryRepository countryRepo;
    private final IssueRepository issueRepo;
    private final LabelRepository labelRepo;
    private final MediaRepository mediaRepo;
    private final ReleaseRepository releaseRepo;

    @Autowired
    IssueController(CountryRepository countryRepo, IssueRepository issueRepo, LabelRepository labelRepo,
                    MediaRepository mediaRepo, ReleaseRepository releaseRepo) {
        this.countryRepo = countryRepo;
        this.issueRepo = issueRepo;
        this.labelRepo = labelRepo;
        this.mediaRepo = mediaRepo;
        this.releaseRepo = releaseRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showIssueList(Model model) {
        model.addAttribute("issues", issueRepo.findAll());

        return "issue/index";
    }

    @GetMapping("/{id}")
    public String showIssueDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<Issue> issue = issueRepo.findById(id);

        issue.ifPresent(v -> model.addAttribute(ISSUE, v));

        return "issue/detail";
    }

    @GetMapping("/add")
    public String showIssueForm(Model model) {
        populateIssueForm(model);
        model.addAttribute(ISSUE, new Issue());
        model.addAttribute(ACTION, "New");

        return ISSUE_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showIssueForm(@PathVariable(value = "id") long id, Model model) {
        populateIssueForm(model);

        Optional<Issue> issue = issueRepo.findById(id);
        issue.ifPresent(v -> model.addAttribute(ISSUE, v));

        model.addAttribute(ACTION, "Edit");

        return ISSUE_FORM;
    }

    @PostMapping("/add")
    public String saveIssue(@Valid Issue issue, BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            populateIssueForm(model);
            model.addAttribute(ACTION, "New");

            return ISSUE_FORM;
        }

        issueRepo.save(issue);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveIssue(@PathVariable("id") long id, @Valid Issue issue, BindingResult bindResult,
                            Model model) {
        if (bindResult.hasErrors()) {
            populateIssueForm(model);
            model.addAttribute(ACTION, "Edit");

            return ISSUE_FORM;
        }

        issue.setIssueId(id);
        issueRepo.save(issue);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteIssue(@PathVariable("id") long id) {
        Issue issue = issueRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid Issue ID: %s", id)));

        issueRepo.delete(issue);

        return REDIRECT_HOME;
    }

    private void populateIssueForm(Model model) {
        model.addAttribute("labels", labelRepo.findAllByOrderByNameAsc());
        model.addAttribute("releases", releaseRepo.findAllByOrderByTitleAsc());
        model.addAttribute("mediaList", mediaRepo.findAllByOrderByNameAsc());
        model.addAttribute("countries", countryRepo.findAllByOrderByCountryNameAsc());
    }
}
