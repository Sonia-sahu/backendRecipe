package com.kunal.practice.controller;

import com.kunal.practice.entity.Recipe;
import com.kunal.practice.service.RecipeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/recipes")
@CrossOrigin(origins = "http://localhost:3000")
public class RecipeController {

    private final RecipeService recipeService;
    private static final Logger logger = LoggerFactory.getLogger(RecipeController.class);

    public RecipeController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @PostMapping("/save")
    public ResponseEntity<Recipe> saveRecipe(@RequestBody Recipe recipe) {
        try {
            // Log request details
            logger.info("Received POST request to save recipe: {}", recipe);
            Recipe savedRecipe = recipeService.saveRecipe(recipe);

            // Log successful response
            logger.info("Recipe saved successfully: {}", savedRecipe);
            return new ResponseEntity<>(savedRecipe, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log any exceptions
            logger.error("Error saving recipe", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/all")
    public ResponseEntity<List<Recipe>> getAllRecipes() {
        try {
            List<Recipe> recipes = recipeService.getAllRecipes();
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        } catch (Exception e) {
            // Log any exceptions
            logger.error("Error retrieving recipes", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/favorite/{id}")
    public ResponseEntity<Recipe> toggleFavoriteStatus(
            @PathVariable String id,
            @RequestBody Map<String, Object> request) {
        try {
            boolean isFavorite = (boolean) request.get("isFavorite");
            String recipeurl = (String) request.get("recipeurl");
            Recipe updatedRecipe = recipeService.toggleFavoriteStatus(id, isFavorite, recipeurl);
            logger.debug("Toggled favorite status for recipe with id {}: {}", id, updatedRecipe);
            return new ResponseEntity<>(updatedRecipe, HttpStatus.OK);
        } catch (Exception e) {
            // Log any exceptions
            logger.error("Error toggling favorite status", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
