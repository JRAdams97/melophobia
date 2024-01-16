package com.jradams.melophobia.controller;

import com.jradams.melophobia.entity.CollectionPhysicalItem;
import com.jradams.melophobia.entity.backing.Quality;
import com.jradams.melophobia.repository.CollectionPhysicalItemRepository;
import com.jradams.melophobia.repository.IssueRepository;
import com.jradams.melophobia.repository.PurchaseRepository;
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
@RequestMapping("/collection-physical")
public class CollectionPhysicalItemController {

    private static final String ACTION = "action";
    private static final String COLLECTION_PHYSICAL_ITEM = "collectionPhysicalItem";
    private static final String COLLECTION_PHYSICAL_ITEM_FORM = "collection-physical/form";
    private static final String REDIRECT_HOME = "redirect:/collection-physical/";

    private final CollectionPhysicalItemRepository collectionPhysicalItemRepo;
    private final IssueRepository issueRepo;
    private final PurchaseRepository purchaseRepo;

    @Autowired
    CollectionPhysicalItemController(CollectionPhysicalItemRepository collectionPhysicalItemRepo,
                                     IssueRepository issueRepo, PurchaseRepository purchaseRepo) {
        this.collectionPhysicalItemRepo = collectionPhysicalItemRepo;
        this.issueRepo = issueRepo;
        this.purchaseRepo = purchaseRepo;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
    }

    @GetMapping("/")
    public String showCollectionPhysicalItemList(Model model) {
        model.addAttribute("collectionPhysicalItems", collectionPhysicalItemRepo.findAll());

        return "collection-physical/index";
    }

    @GetMapping("/add")
    public String showCollectionPhysicalItemForm(Model model) {
        populateCollectionPhysicalItemForm(model);

        model.addAttribute(COLLECTION_PHYSICAL_ITEM, new CollectionPhysicalItem());
        model.addAttribute(ACTION, "New");

        return COLLECTION_PHYSICAL_ITEM_FORM;
    }

    @GetMapping("/edit/{id}")
    public String showCollectionPhysicalItemForm(@PathVariable(value = "id") long id, Model model) {
        populateCollectionPhysicalItemForm(model);

        Optional<CollectionPhysicalItem> collectionPhysicalItem = collectionPhysicalItemRepo.findById(id);
        collectionPhysicalItem.ifPresent(v -> model.addAttribute(COLLECTION_PHYSICAL_ITEM, v));

        model.addAttribute(ACTION, "Edit");

        return COLLECTION_PHYSICAL_ITEM_FORM;
    }

    @PostMapping("/add")
    public String saveCollectionPhysicalItem(@Valid CollectionPhysicalItem physicalItem, BindingResult bindResult,
                                             Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "New");
            populateCollectionPhysicalItemForm(model);

            return COLLECTION_PHYSICAL_ITEM_FORM;
        }

        collectionPhysicalItemRepo.save(physicalItem);

        return REDIRECT_HOME;
    }

    @PostMapping("/edit/{id}")
    public String saveCollectionPhysicalItem(@PathVariable("id") long id, @Valid CollectionPhysicalItem physicalItem,
                                             BindingResult bindResult, Model model) {
        if (bindResult.hasErrors()) {
            model.addAttribute(ACTION, "Edit");
            populateCollectionPhysicalItemForm(model);

            return COLLECTION_PHYSICAL_ITEM_FORM;
        }

        physicalItem.setPhysicalCollectionId(id);
        collectionPhysicalItemRepo.save(physicalItem);

        return REDIRECT_HOME;
    }

    @GetMapping("/delete/{id}")
    public String deleteCollectionPhysicalItem(@PathVariable("id") long id) {
        CollectionPhysicalItem collectionPhysicalItem = collectionPhysicalItemRepo.findById(id).orElseThrow(
                () -> new IllegalArgumentException(String.format("Invalid CollectionPhysicalItem ID: %s", id)));

        collectionPhysicalItemRepo.delete(collectionPhysicalItem);

        return REDIRECT_HOME;
    }

    private void populateCollectionPhysicalItemForm(Model model) {
        model.addAttribute("issues", issueRepo.findAll());
        model.addAttribute("purchases", purchaseRepo.findAllByOrderByPriceAsc());
        model.addAttribute("qualities", Quality.values());
    }
}
