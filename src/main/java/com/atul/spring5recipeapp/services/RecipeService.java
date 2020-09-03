package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.model.Recipe;

import java.util.Set;

public interface RecipeService {
    Set<Recipe> getRecipes();
}
