package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.commands.RecipeCommands;
import com.atul.spring5recipeapp.services.ImageService;
import com.atul.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class ImageControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    ImageService imageService;

    ImageController imageController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        imageController=new ImageController(recipeService,imageService);
        mockMvc= MockMvcBuilders.standaloneSetup(imageController).build();
    }

    @Test
    void showUploadForm() throws Exception {
        //given
        RecipeCommands recipeCommands=new RecipeCommands();
        recipeCommands.setId(1L);

        //when
        when(recipeService.findCommandById(anyLong())).thenReturn(recipeCommands);

        //then
        mockMvc.perform(get("/recipe/1/image"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("recipe"))
                .andExpect(view().name("recipe/image/imageUploadForm"));

        verify(recipeService,times(1)).findCommandById(anyLong());
    }

    @Test
    void changeImage() throws Exception {
        MockMultipartFile mockMultipartFile=new MockMultipartFile("imagefile","testing.txt",
                "text/plain","Hello Atul here".getBytes());

        mockMvc.perform(multipart("/recipe/1/image").file(mockMultipartFile))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string("Location","/recipe/1/show"));

        verify(imageService,times(1)).saveImage(anyLong(),any());
    }
}