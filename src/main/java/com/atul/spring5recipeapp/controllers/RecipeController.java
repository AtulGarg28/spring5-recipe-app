package com.atul.spring5recipeapp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RecipeController {

    @RequestMapping({"/","recipe"})
    public String showRecipe(){
        System.out.println("Some message.... 5456456kvddl mksfkn");
        return "index";
    }
}
