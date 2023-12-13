package com.kunal.practice.entity;

import javax.persistence.*;

@Entity
@Table(name = "recipe")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String title;
    // Add more fields as needed

    // Constructors, getters, and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setFavorite(boolean isFavorite) {
    }

    public void setRecipeUrl(String recipeUrl) {
    }

    // Add more fields, getters, and setters as needed
}
