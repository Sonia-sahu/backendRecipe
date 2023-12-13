package com.kunal.practice.repository;

import com.kunal.practice.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, String> {

    Optional<Recipe> findById(String id);
}