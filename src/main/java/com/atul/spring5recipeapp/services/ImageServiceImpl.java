package com.atul.spring5recipeapp.services;

import com.atul.spring5recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
public class ImageServiceImpl implements ImageService {

    private final RecipeRepository recipeRepository;
    private final RecipeService recipeService;

    public ImageServiceImpl(RecipeRepository recipeRepository, RecipeService recipeService) {
        this.recipeRepository = recipeRepository;
        this.recipeService = recipeService;
    }

    @Override
    public void saveImage(Long recipe_id, MultipartFile file) {
        log.debug("Recieved an image.");
//        Recipe recipe=recipeService.findById(recipe_id);
//        recipe.
    }
}
