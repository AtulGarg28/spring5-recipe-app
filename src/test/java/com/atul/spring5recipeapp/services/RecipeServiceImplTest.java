package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.model.Recipe;
import com.atul.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    RecipeServiceImpl recipeService;

    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    void setUp() throws Exception{
        MockitoAnnotations.initMocks(this);
        recipeService=new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipesTest() throws Exception{
        Recipe recipe=new Recipe();
        HashSet<Recipe> recipiesData=new HashSet();
        recipiesData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipiesData);

        Set<Recipe> recipeSet=recipeService.getRecipes();
        assertEquals(recipeSet.size(),1);
        verify(recipeRepository,times(1)).findAll();
        verify(recipeRepository,never()).findById(anyLong());
    }

    @Test
    void getRecipeByIdTest() {
        Recipe recipe=new Recipe();
        recipe.setId(1L);
        Optional<Recipe> optionalRecipe=Optional.of(recipe);
        when(recipeRepository.findById(anyLong())).thenReturn(optionalRecipe);
        Recipe returnedRecipe=recipeService.findById(1L);
//        assertNotNull(returnedRecipe,"Null Recipe Returned");
//        verify(recipeRepository,times(1)).findById(anyLong());
//        verify(recipeRepository,never()).findAll();
    }
}