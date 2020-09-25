package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.IngredientCommands;
import com.atul.spring5recipeapp.commands.RecipeCommands;
import com.atul.spring5recipeapp.services.IngredientService;
import com.atul.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    IngredientService ingredientService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController=new IngredientController(recipeService, ingredientService);
        mockMvc= MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void showListOfIngredientsTest() throws Exception {

        //given
        RecipeCommands recipeCommands=new RecipeCommands();
        recipeCommands.setId(2L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommands);

        //then
        mockMvc.perform(get("/recipe/2/ingredients"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/list"))
                .andExpect(model().attributeExists("recipe"));

        verify(recipeService,times(1)).findCommandById(anyLong());
    }

    @Test
    void ViewIngredientTest() throws Exception {
        //given
        IngredientCommands ingredientCommands=new IngredientCommands();
        //when
        when(ingredientService.findByRecipeIdAndIngredientId(anyLong(),anyLong())).thenReturn(ingredientCommands);
        //then
        mockMvc.perform(get("/recipe/1/ingredient/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("ingredient/show"))
                .andExpect(model().attributeExists("ingredient"));
    }
}