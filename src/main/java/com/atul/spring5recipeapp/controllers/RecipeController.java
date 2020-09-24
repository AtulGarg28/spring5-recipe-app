package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.RecipeCommands;
import com.atul.spring5recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class RecipeController {

    public RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping("/recipe/show/{id}")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommands());
        return "recipe/recipeForm";
    }

    @PostMapping
    @RequestMapping("recipe")           //Here, it is name="recipe", Thus it is used for the submit button
    public String saveOrUpdate(@ModelAttribute RecipeCommands commands){
        RecipeCommands savedRecipeCommands= recipeService.saveRecipeCommand(commands);

        return "redirect:/recipe/show/"+savedRecipeCommands.getId();
    }
}