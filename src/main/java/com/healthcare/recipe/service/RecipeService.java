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

    //음식 id가 있는지 확인 한 후에 레시피를 생성을 해야한다.
   public Recipe createRecipe(Recipe recipe, Long foodId) {
        Food food = foodRepository.findById(foodId).orElseThrow(()-> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        recipe.setFood(food);
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




}
