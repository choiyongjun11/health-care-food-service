package com.healthcare.recipe.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.entity.Food;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.repository.RecipeRepository;
import net.bytebuddy.description.type.TypeDescription;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ThemeResolver;

import java.util.Optional;

@Service
public class RecipeService {
    private final FoodRepository foodRepository;

    //create, find, update, delete
    private RecipeRepository recipeRepository;

    public RecipeService(RecipeRepository recipeRepository, FoodRepository foodRepository) {
        this.recipeRepository = recipeRepository;
        this.foodRepository = foodRepository;

    }

   public Recipe createRecipe(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    public Recipe findRecipe(long recipeId) {
        return findVerifiedRecipe(recipeId);
    }

    public Recipe updateRecipe(Recipe recipe) {
        Recipe findRecipe = findVerifiedRecipe(recipe.getRecipeId());

        if(recipe.getProcess() != null) {
            findRecipe.setProcess(recipe.getProcess());
        }
        if(recipe.getDifficulty() != null) {
            findRecipe.setDifficulty(recipe.getDifficulty());
        }

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

    //생성 시 이미 존재한지 확인 하는 방법이 필요할까나.




}
