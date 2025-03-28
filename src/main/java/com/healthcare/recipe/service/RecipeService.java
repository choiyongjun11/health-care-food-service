package com.healthcare.recipe.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.repository.RecipeRepository;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecipeService {
    //create, find, update, delete
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public Recipe createRecipe(Recipe recipe) {
        verifyExistRecipe(recipe.getRecipeName());
        Recipe saveRecipe = recipeRepository.save(recipe);
        return saveRecipe;
    }

    public Recipe findRecipe(long recipeId) {
        return findVerifiedRecipe(recipeId);
    }

    public Recipe updateRecipe(Recipe recipe) {
        Recipe findRecipe = findVerifiedRecipe(recipe.getRecipeId());
        Optional.ofNullable(recipe.getRecipeName()).ifPresent(recipeName -> findRecipe.setRecipeName(recipeName));
        return recipeRepository.save(findRecipe);
    }

    public void deleteRecipe(long recipeId) {
        Recipe recipe = recipeRepository.findById(recipeId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        recipeRepository.delete(recipe);
    }

    public Recipe findVerifiedRecipe(long recipeId) { //현재 존재하는지 확인하는 기능 find, update, delete
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId); //npe 발생 방지 optional 사용
        Recipe findRecipe = optionalRecipe.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findRecipe;
    }

    private void verifyExistRecipe(String recipeName) { //생성 시 이미 존재하는지 확인하는 기능 (create)
        Optional<Recipe> recipe = recipeRepository.findByRecipeName(recipeName);
        if(recipe.isPresent()) throw new BusinessLogicException(ExceptionCode.NOT_FOUND);
    }

}
