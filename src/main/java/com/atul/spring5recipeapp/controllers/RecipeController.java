package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.RecipeCommands;
import com.atul.spring5recipeapp.services.RecipeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class RecipeController {

    public RecipeService recipeService;

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @GetMapping
    @RequestMapping("/recipe/{id}/show")
    public String showById(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findById(new Long(id)));
        return "recipe/show";
    }

    @GetMapping
    @RequestMapping("recipe/new")
    public String newRecipe(Model model){
        model.addAttribute("recipe",new RecipeCommands());
        return "recipe/recipeForm";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/update")
    public String updateRecipe(@PathVariable String id, Model model){
        model.addAttribute("recipe",recipeService.findCommandById(Long.valueOf(id)));
        return "recipe/recipeForm";
    }

    // THIS METHOD IS CALLED WHEN SUBMIT BUTTON IS CLICKED.
    @PostMapping
    @RequestMapping("recipe")
    public String saveOrUpdate(@ModelAttribute RecipeCommands commands){
        RecipeCommands savedRecipeCommands= recipeService.saveRecipeCommand(commands);

        return "redirect:/recipe/" +savedRecipeCommands.getId() +"/show";
    }

    @GetMapping
    @RequestMapping("recipe/{id}/delete")
    public String deleteRecipe(@PathVariable String id, Model model){
        log.debug("Deleting recipe of id: "+id);
        recipeService.deleteRecipeById(Long.valueOf(id));
        return "redirect:/";
    }
}