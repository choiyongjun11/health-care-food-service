package com.healthcare.recipe.controller;

import com.healthcare.food.entity.Food;
import com.healthcare.recipe.dto.RecipeDto;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.mapper.RecipeMapper;
import com.healthcare.recipe.repository.RecipeRepository;
import com.healthcare.recipe.service.RecipeService;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.utils.UriCreator;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/foods")
public class RecipeController {
    private final static String RECIPE_DEFAULT_URL = "/recipes";
    private final RecipeService recipeService;
    private final RecipeMapper mapper;

    public RecipeController(RecipeService recipeService,RecipeMapper mapper) {
        this.recipeService = recipeService;
        this.mapper = mapper;
    }

    @PostMapping("/foods/{food-id}/recipes")
    public ResponseEntity postRecipe(@PathVariable("food-id") Long foodId,
                                     @RequestBody @Valid RecipeDto.Post requestBody) {
        Recipe recipe = mapper.RecipePostToRecipe(requestBody); //
        Recipe createdRecipe = recipeService.createRecipe(recipe, foodId);
        URI location = UriCreator.createUri(RECIPE_DEFAULT_URL, createdRecipe.getRecipeId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{food-id}/recipes/{recipe-id}")
    public ResponseEntity patchRecipe(@PathVariable("food-id") long foodId,
                                      @PathVariable("recipe-id") long recipeId,
                                      @RequestBody @Valid RecipeDto.Patch requestBody) {
        requestBody.setRecipeId(recipeId);
        Recipe updated = recipeService.updateRecipe(mapper.RecipePatchToRecipe(requestBody), foodId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.recipeToRecipeResponse(updated)), HttpStatus.OK);
    }

    @GetMapping("/{food-id}/recipes")
    public ResponseEntity getRecipeByFood(@PathVariable("food-id") long foodId) {
        Recipe recipe = recipeService.findRecipeByFoodId(foodId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.recipeToRecipeResponse(recipe)), HttpStatus.OK);
    }

    @DeleteMapping("/{food-id}/recipes/{recipe-id}")
    public ResponseEntity deleteRecipe(@PathVariable("food-id") long foodId,
                                       @PathVariable("recipe-id") long recipeId) {
        recipeService.deleteRecipe(recipeId, foodId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
