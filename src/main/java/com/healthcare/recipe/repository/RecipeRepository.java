package com.healthcare.recipe.repository;

import com.healthcare.recipe.entity.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
    Optional<Recipe> findByRecipeName (String recipeName);
}
