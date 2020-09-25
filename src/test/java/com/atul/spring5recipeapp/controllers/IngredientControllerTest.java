package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.RecipeCommands;
import com.atul.spring5recipeapp.model.Recipe;
import com.atul.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class IngredientControllerTest {

    @Mock
    RecipeService recipeService;

    IngredientController ingredientController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        ingredientController=new IngredientController(recipeService);
        mockMvc= MockMvcBuilders.standaloneSetup(ingredientController).build();
    }

    @Test
    void showIngredientsTest() throws Exception {

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
}