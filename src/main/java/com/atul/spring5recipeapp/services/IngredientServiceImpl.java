package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.commands.IngredientCommands;
import com.atul.spring5recipeapp.converters.IngredientToIngredientCommands;
import com.atul.spring5recipeapp.model.Recipe;
import com.atul.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class IngredientServiceImpl implements IngredientService{

    private final RecipeRepository recipeRepository;
    private final IngredientToIngredientCommands ingredientToIngredientCommands;

    public IngredientServiceImpl(RecipeRepository recipeRepository, IngredientToIngredientCommands ingredientToIngredientCommands) {
        this.recipeRepository = recipeRepository;
        this.ingredientToIngredientCommands = ingredientToIngredientCommands;
    }

    @Override
    public IngredientCommands findByRecipeIdAndIngredientId(Long recipe_id, Long ingredient_id) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(recipe_id);

        if (!optionalRecipe.isPresent()){
            log.error("recipe id: "+recipe_id+" not found");
        }

        Recipe recipe=optionalRecipe.get();

        Optional<IngredientCommands> optionalIngredientCommands=recipe.getIngredients().stream()
                .filter(ingredient -> ingredient.getId().equals(ingredient_id))
                .map(ingredient -> ingredientToIngredientCommands.convert(ingredient)).findFirst();

        if (!optionalIngredientCommands.isPresent()){
            log.error("Ingredient of id: "+ingredient_id+" not found.");
        }

        return optionalIngredientCommands.get();
    }
}
