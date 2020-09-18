package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.model.Category;
import com.atul.spring5recipeapp.model.UnitOfMeasure;
import com.atul.spring5recipeapp.repositories.CategoryRepository;
import com.atul.spring5recipeapp.repositories.UnitOfMeasureRepository;
import com.atul.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Slf4j
@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"/","index"})
    public String getIndexPage(Model model){

        log.debug("I am in the Controller.");
        model.addAttribute("recipes",recipeService.getRecipes());
        return "index";
    }
}