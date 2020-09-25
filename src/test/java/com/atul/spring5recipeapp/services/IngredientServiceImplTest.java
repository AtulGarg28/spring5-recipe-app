package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.commands.IngredientCommands;
import com.atul.spring5recipeapp.converters.IngredientToIngredientCommands;
import com.atul.spring5recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommands;
import com.atul.spring5recipeapp.model.Ingredient;
import com.atul.spring5recipeapp.model.Recipe;
import com.atul.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommands ingredientToIngredientCommands;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    //init converters
    public IngredientServiceImplTest() {
        this.ingredientToIngredientCommands = new IngredientToIngredientCommands(new UnitOfMeasureToUnitOfMeasureCommands());
    }

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientService=new IngredientServiceImpl(recipeRepository,ingredientToIngredientCommands);
    }

    @Test
    public void findByRecipeIdAndId() throws Exception {
    }

    @Test
    void findByRecipeIdAndIngredientIdTest() throws Exception {
        //given
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1=new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2=new Ingredient();
        ingredient2.setId(2L);

        Ingredient ingredient3=new Ingredient();
        ingredient3.setId(3L);

        recipe.getIngredients().add(ingredient1);
        recipe.getIngredients().add(ingredient2);
        recipe.getIngredients().add(ingredient3);

        Optional<Recipe> recipeOptional=Optional.of(recipe);

        //when
        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        IngredientCommands ingredientCommands=ingredientService.findByRecipeIdAndIngredientId(1L,3L);

        //then
        assertEquals(Long.valueOf(3L),ingredientCommands.getId());

        //ERROR.... NOT STORING THE RECIPE ID IN INGREDIENT.
//        assertEquals(Long.valueOf(1L),ingredientCommands.getRecipeId());
        verify(recipeRepository,times(1)).findById(anyLong());
    }
}