package com.kunal.practice.service;

import com.kunal.practice.entity.Recipe;
import com.kunal.practice.repository.RecipeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;

    public Recipe saveRecipe(Recipe recipe) {
        // Additional logic if needed
        return recipeRepository.save(recipe);
    }

    public List<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    public Recipe toggleFavoriteStatus(String id, boolean isFavorite, String recipeUrl) {
        String numericPart = extractNumericPart(id);
        String recipeId = numericPart;

        if (isFavorite) {
            // Create a new Recipe object
            Recipe newRecipe = new Recipe();
            newRecipe.setId(recipeId);
            newRecipe.setFavorite(true);
            newRecipe.setRecipeUrl(recipeUrl);

            // Save the new recipe to the database
            return saveRecipe(newRecipe);
        } else {
            // Handle the case where the recipe is no longer a favorite (optional)
            // You can implement additional logic here if needed
            return null;
        }
    }

    private String extractNumericPart(String id) {
        return id.replaceAll("[^\\d]", "");
    }
}
