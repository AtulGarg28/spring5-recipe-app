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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class RecipeControllerTest {

    @Mock
    RecipeService recipeService;

    RecipeController recipeController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        recipeController=new RecipeController(recipeService);
        mockMvc=MockMvcBuilders.standaloneSetup(recipeController).build();
    }

    @Test
    void showById() throws Exception {
        Recipe recipe=new Recipe();
        recipe.setId(1L);

        when(recipeService.findById(anyLong())).thenReturn(recipe);

        mockMvc.perform(get("/recipe/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/show"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testGetNewRecipeForm() throws Exception {

        mockMvc.perform(get("/recipe/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("recipe/recipeForm"))
                .andExpect(model().attributeExists("recipe"));
    }

    @Test
    void testPostNewRecipeForm() throws Exception {

        RecipeCommands recipeCommands=new RecipeCommands();
        recipeCommands.setId(2L);

        when(recipeService.saveRecipeCommand(any())).thenReturn(recipeCommands);

        mockMvc.perform(post("/recipe"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/recipe/2/show"));
    }

    @Test
    void testGetUpdateView() throws Exception {

        RecipeCommands recipeCommands=new RecipeCommands();
        recipeCommands.setId(2L);


    }
}