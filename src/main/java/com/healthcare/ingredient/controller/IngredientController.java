package com.healthcare.ingredient.controller;

import com.healthcare.ingredient.dto.IngredientDto;
import com.healthcare.ingredient.entity.Ingredient;
import com.healthcare.ingredient.mapper.IngredientMapper;
import com.healthcare.ingredient.service.IngredientService;
import com.healthcare.response.MultiResponseDto;
import com.healthcare.response.SingleResponseDto;
import com.healthcare.utils.UriCreator;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ingredients")
public class IngredientController {

    private final static String INGREDIENT_DEFAULT_URL = "/ingredients";
    private final IngredientService ingredientService;
    private final IngredientMapper mapper;
    public IngredientController(IngredientService ingredientService, IngredientMapper mapper) {
        this.ingredientService = ingredientService;
        this.mapper = mapper;

    }

    @PostMapping
    public ResponseEntity postIngredient(@RequestBody IngredientDto.Post requestBody) {
        Ingredient ingredient = mapper.ingredientPostToIngredient(requestBody);
        Ingredient createdIngredient = ingredientService.createIngredient(ingredient);
        URI location = UriCreator.createUri(INGREDIENT_DEFAULT_URL, createdIngredient.getIngredientId());
        return ResponseEntity.created(location).build();
    }

    @PatchMapping("/{ingredient-id}")
    public ResponseEntity patchIngredient(@PathVariable("ingredient-id") long ingredientId, @RequestBody IngredientDto.Patch requestBody) {
        requestBody.setIngredientId(ingredientId);
        Ingredient ingredient = ingredientService.updateIngredient(mapper.ingredientPatchToIngredient(requestBody));
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ingredientToIngredientResponse(ingredient)),HttpStatus.OK);
    }

    @GetMapping("/{ingredient-id}")
    public ResponseEntity getIngredient(@PathVariable("ingredient-id") long ingredientId) {
        Ingredient ingredient = ingredientService.findIngredient(ingredientId);
        return new ResponseEntity<>(new SingleResponseDto<>(mapper.ingredientToIngredientResponse(ingredient)),HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> getIngredients(@RequestParam int page, @RequestParam int size){
        Page<Ingredient> ingredientPage = ingredientService.findIngredients(page, size);
        List<IngredientDto.Response> responses = ingredientPage.getContent()
                .stream()
                .map(mapper::ingredientToIngredientResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new MultiResponseDto<>(responses, ingredientPage));
    }

    @DeleteMapping("/{ingredient-id}")
    public ResponseEntity deleteIngredient(@PathVariable("ingredient-id") long ingredientId){
        ingredientService.deleteIngredient(ingredientId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
