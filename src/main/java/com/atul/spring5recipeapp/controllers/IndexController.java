package com.atul.spring5recipeapp.controllers;

import com.atul.spring5recipeapp.model.Category;
import com.atul.spring5recipeapp.model.UnitOfMeasure;
import com.atul.spring5recipeapp.repositories.CategoryRepository;
import com.atul.spring5recipeapp.repositories.UnitOfMeasureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private CategoryRepository categoryRepository;
    private UnitOfMeasureRepository unitOfMeasureRepository;

    public IndexController(CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @RequestMapping({"/","index"})
    public String getIndexPage(){

        Optional<Category> categoryOptional=categoryRepository.findByDescription("Mexican");
        Optional<UnitOfMeasure> unitOfMeasureOptional=unitOfMeasureRepository.findByDescription("TableSpoon");

        System.out.println("Category id is "+categoryOptional.get().getId());
        System.out.println("UOM id is "+unitOfMeasureOptional.get().getId());

        return "index";
    }
}
