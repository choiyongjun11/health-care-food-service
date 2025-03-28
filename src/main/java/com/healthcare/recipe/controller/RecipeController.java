package com.healthcare.recipe.controller;

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

import java.net.URI;

@RestController
@RequestMapping("/recipes")
public class RecipeController {
    private final static String RECIPE_DEFAULT_URL = "/recipes";
    private final RecipeService recipeService;
    private final RecipeMapper mapper;

    public RecipeController(RecipeService recipeService,RecipeMapper mapper) {
        this.recipeService = recipeService;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity postRecipe(@RequestBody RecipeDto.Post requestBody) {
        Recipe recipe = mapper.RecipePostToRecipe(requestBody);
        Recipe createRecipe = recipeService.createRecipe(recipe);
        URI location = UriCreator.createUri(RECIPE_DEFAULT_URL, createRecipe.getRecipeId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{recipes-id}")
    public ResponseEntity patchRecipe(@PathVariable("recipe-id") long recipeId,  @RequestBody RecipeDto.Patch requestBody) {
        requestBody.setRecipeId(recipeId);
        Recipe recipe = recipeService.updateRecipe(mapper.RecipePatchToRecipe(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.recipeToRecipeResponse(recipe)),HttpStatus.OK);
    }

    @GetMapping("/{recipes-id}")
    public ResponseEntity getRecipe(@PathVariable("recipe-id") long recipeId) {
        Recipe recipe = recipeService.findRecipe(recipeId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.recipeToRecipeResponse(recipe)), HttpStatus.OK);
    }

    @DeleteMapping("/{recipes-id}")
    public ResponseEntity deleteRecipe(@PathVariable("recipe-id") long recipeId) {
        recipeService.deleteRecipe(recipeId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}
