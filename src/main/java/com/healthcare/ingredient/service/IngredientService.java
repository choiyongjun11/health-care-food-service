package com.healthcare.ingredient.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.ingredient.entity.Ingredient;
import com.healthcare.ingredient.repository.IngredientRepository;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.Optional;

@Service
public class IngredientService {
    //create, find, update, delete
    private IngredientRepository ingredientRepository;

    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public Ingredient createIngredient(Ingredient ingredient) {
        verifyExistIngredient(ingredient.getIngredientName());
        Ingredient saveIngredient = ingredientRepository.save(ingredient);
        return saveIngredient;
    }

    public Ingredient findIngredient(long ingredientId) {
        return findVerifiedIngredient(ingredientId);

    }

    public Ingredient updateIngredient(Ingredient ingredient) {
        Ingredient findIngredient = findVerifiedIngredient(ingredient.getIngredientId());
        Optional.ofNullable(ingredient.getIngredientName()).ifPresent(ingredientName -> findIngredient.setIngredientName(ingredientName));
        return ingredientRepository.save(findIngredient);

    }

    public void deleteIngredient(long ingredientId) {
        Ingredient ingredient = findVerifiedIngredient(ingredientId);
        ingredientRepository.delete(ingredient);
    }

    //현재 존재하는지 확인하는 기능 (find, update, delete)
    public Ingredient findVerifiedIngredient(long ingredientId) {
        Optional<Ingredient> optionalIngredient = ingredientRepository.findById(ingredientId);
        Ingredient findIngredient = optionalIngredient.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findIngredient;
    }

    //생성 시 이미 존재하는지 확인하는 기능 (create)
    private void verifyExistIngredient(String ingredientName) {
        Optional<Ingredient> ingredient = ingredientRepository.findByIngredientName(ingredientName);
        if(ingredient.isPresent()) throw new BusinessLogicException(ExceptionCode.NOT_FOUND);

    }

}
