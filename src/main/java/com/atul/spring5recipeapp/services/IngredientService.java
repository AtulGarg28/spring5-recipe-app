package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.commands.IngredientCommands;

public interface IngredientService {
    IngredientCommands findByRecipeIdAndIngredientId(Long recipe_id, Long ingredient_id);
}
