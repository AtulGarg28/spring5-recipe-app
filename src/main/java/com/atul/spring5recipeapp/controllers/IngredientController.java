package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.services.IngredientService;
import com.atul.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping("/recipe/{recipe_id}/ingredients")
    public String showListOfIngredients(@PathVariable String recipe_id, Model model){
        log.debug("Getting list of ingredients on the basis of recipe id: "+recipe_id);
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(recipe_id)));
        return "ingredient/list";
    }

    @GetMapping
    @RequestMapping("/recipe/{recipe_id}/ingredient/{ingredient_id}/show")
    public String ViewIngredient(@PathVariable String recipe_id,@PathVariable String ingredient_id, Model model){
        log.debug("Getting list of ingredients on the basis of recipe id: "+recipe_id);
        model.addAttribute("ingredient",ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipe_id),Long.valueOf(ingredient_id)));


        return "ingredient/show";
    }

}
