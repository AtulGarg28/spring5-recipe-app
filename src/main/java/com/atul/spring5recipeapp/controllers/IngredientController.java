package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.IngredientCommands;
import com.atul.spring5recipeapp.services.IngredientService;
import com.atul.spring5recipeapp.services.RecipeService;
import com.atul.spring5recipeapp.services.UnitOfMeasureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
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

    @GetMapping
    @RequestMapping("/recipe/{recipe_id}/ingredient/{ingredient_id}/update")
    public String UpdateIngredient(@PathVariable String recipe_id,
                                   @PathVariable String ingredient_id, Model model){
        log.debug("updating ingredient on the basis of recipe id: "+recipe_id);
        IngredientCommands ingredientCommands=ingredientService.findByRecipeIdAndIngredientId(Long.valueOf(recipe_id),Long.valueOf(ingredient_id));
        model.addAttribute("ingredient",ingredientCommands);
        model.addAttribute("uomList",unitOfMeasureService.listAllUoms());
        return "ingredient/ingredientForm";
    }

    @PostMapping("/recipe/{recipe_id}/ingredient")
    public String showSavedOrUpdateIngredient(@ModelAttribute(name = "ingredient") IngredientCommands command,@PathVariable String recipe_id){
//        System.out.println(command.getId());
//        System.out.println("R: id"+command.getRecipeId());
//        System.out.println("Desc"+command.getDescription());
//        System.out.println("UOM: id"+command.getUom().getId());
//        System.out.println("Amount"+command.getAmount());
//        System.out.println("recipe_id"+recipe_id);
        command.setRecipeId(Long.valueOf(recipe_id));
        IngredientCommands savedIngredientCommands=ingredientService.saveIngredientCommand(command);
        log.debug("saved recipe id:"+savedIngredientCommands.getRecipeId());
        log.debug("saved ingredient id:"+savedIngredientCommands.getId());
        return "redirect:/recipe/" + savedIngredientCommands.getRecipeId() + "/ingredient/" + savedIngredientCommands.getId() + "/show";
//        return "redirect:/";
    }
}
