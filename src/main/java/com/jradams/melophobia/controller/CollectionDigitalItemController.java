package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.CollectionDigitalItem;
import com.jradams.melophobia.entity.backing.Status;
import com.jradams.melophobia.repository.CollectionDigitalItemRepository;
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
@RequestMapping("/collection-digital")
public class CollectionDigitalItemController {

    private static final String ACTION = "action";
    private static final String COLLECTION_DIGITAL_ITEM = "collectionDigitalItem";
    private static final String COLLECTION_DIGITAL_ITEM_FORM = "collection-digital/form";
    private static final String REDIRECT_HOME = "redirect:/collection-digital/";

    private final CollectionDigitalItemRepository collectionDigitalItemRepo;
    private final MediaRepository mediaRepo;
    private final ReleaseRepository releaseRepo;

    @Autowired
    CollectionDigitalItemController(CollectionDigitalItemRepository collectionDigitalItemRepo,
                                    MediaRepository mediaRepo, ReleaseRepository releaseRepo) {
        this.collectionDigitalItemRepo = collectionDigitalItemRepo;
        this.mediaRepo = mediaRepo;
        this.releaseRepo = releaseRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showCollectionDigitalItemList(Model model) {
        model.addAttribute("collectionDigitalItems", collectionDigitalItemRepo.findAll());

        return "collection-digital/index";
    }

    @GetMapping("/{id}")
    public String showCollectionDigitalItemDetail(@PathVariable(value = "id") long id, Model model) {
        Optional<CollectionDigitalItem> collectionDigitalItem = collectionDigitalItemRepo.findById(id);
        collectionDigitalItem.ifPresent(v -> model.addAttribute(COLLECTION_DIGITAL_ITEM, v));

        return "collection-digital/detail";
    }

    @GetMapping("/add")
    public String showCollectionDigitalItemForm(Model model) {
        populateCollectionDigitalItemForm(model);

        model.addAttribute(COLLECTION_DIGITAL_ITEM, new CollectionDigitalItem());
        model.addAttribute(ACTION, "New");

        return COLLECTION_DIGITAL_ITEM_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showCollectionDigitalItemForm(@PathVariable(value = "id") long id, Model model) {
        populateCollectionDigitalItemForm(model);

        Optional<CollectionDigitalItem> collectionDigitalItem = collectionDigitalItemRepo.findById(id);
        collectionDigitalItem.ifPresent(v -> model.addAttribute(COLLECTION_DIGITAL_ITEM, v));

        model.addAttribute(ACTION, "Edit");

        return COLLECTION_DIGITAL_ITEM_FORM;
    }

    @PostMapping("/add")
    public String saveCollectionDigitalItem(@Valid CollectionDigitalItem digitalItem, BindingResult bindResult,
                                             Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");
            populateCollectionDigitalItemForm(model);

            return COLLECTION_DIGITAL_ITEM_FORM;
        }

        collectionDigitalItemRepo.save(digitalItem);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveCollectionDigitalItem(@PathVariable("id") long id, @Valid CollectionDigitalItem digitalItem,
                                            BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");
            populateCollectionDigitalItemForm(model);

            return COLLECTION_DIGITAL_ITEM_FORM;
        }

        digitalItem.setDigitalCollectionId(id);
        collectionDigitalItemRepo.save(digitalItem);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteCollectionDigitalItem(@PathVariable("id") long id) {
        CollectionDigitalItem collectionDigitalItem = collectionDigitalItemRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid CollectionDigitalItem ID: %s", id)));

        collectionDigitalItemRepo.delete(collectionDigitalItem);

        return REDIRECT_HOME;
    }

    private void populateCollectionDigitalItemForm(Model model) {
        model.addAttribute("mediaList", mediaRepo.findAllByOrderByNameAsc());
        model.addAttribute("releases", releaseRepo.findAllByOrderByTitleAsc());
        model.addAttribute("statuses", Status.values());
    }
}
