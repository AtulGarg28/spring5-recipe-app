package com.atul.spring5recipeapp.commands;

import com.atul.spring5recipeapp.model.Difficulty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommands {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Difficulty difficulty;
    private Set<IngredientCommands> ingredients=new HashSet<>();
//    private Byte[] image;
    private NotesCommands notes;
    private Set<CategoryCommands> categories=new HashSet<>();
}
