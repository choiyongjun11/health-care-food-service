package com.healthcare.recipe.service;

import com.healthcare.exception.BusinessLogicException;
import com.healthcare.exception.ExceptionCode;
import com.healthcare.food.entity.Food;
import com.healthcare.food.repository.FoodRepository;
import com.healthcare.recipe.entity.Recipe;
import com.healthcare.recipe.entity.RecipeStep;
import com.healthcare.recipe.repository.RecipeRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Recipe createRecipe(Recipe recipe, Long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        //이미 등록된 레시피인지 확인
        Optional<Recipe> existing = recipeRepository.findByFood(food);
        if (existing.isPresent()) {
            throw new BusinessLogicException(ExceptionCode.RECIPE_ALREADY_EXISTS);
        }
        recipe.setFood(food);

        //관계 매핑 확인
        if (recipe.getProcess() != null) {
            for (RecipeStep step : recipe.getProcess()) {
                step.setRecipe(recipe);
            }
        }

        //totalcookingtime 계산 구현
        String totalCookingTime = recipe.getProcess().stream()
                .mapToInt(RecipeStep::getCooktime)
                .sum() + "분";
        recipe.setTotalCookingTime(totalCookingTime);

        return recipeRepository.save(recipe);
    }

    public Recipe findRecipeByFoodId(long foodId) {
        Food food = foodRepository.findById(foodId)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));

        return recipeRepository.findByFood(food)
                .orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
    }

    public Recipe updateRecipe(Recipe recipe, long foodId) {
        Recipe findRecipe = findVerifiedRecipe(recipe.getRecipeId());

        // foodId 검증
        if (findRecipe.getFood().getFoodId() != foodId) {
            throw new BusinessLogicException(ExceptionCode.FORBIDDEN);
        }

        // 기존 step 비우고 새로 설정 (orphanRemoval = true 로 자동 삭제)
        if (recipe.getProcess() != null) {
            // 1. 기존 step 초기화 (DB에서도 삭제됨)
            findRecipe.getProcess().clear();

            // 2. 새로운 step 리스트에 recipe 연관관계 연결
            for (RecipeStep step : recipe.getProcess()) {
                step.setRecipe(findRecipe); // 양방향 관계 설정
                findRecipe.getProcess().add(step); //리스트에 추가
            }
        }

        // 난이도 업데이트
        if (recipe.getDifficulty() != null) {
            findRecipe.setDifficulty(recipe.getDifficulty());
        }

        return recipeRepository.save(findRecipe);
    }


    /*
    25.04.06 Delete 시 RecipeStep 기록만 날라가며 부모 객체인 RecipeId 는 삭제가 불가능합니다.
    그 이유는 data.sql 로 더미 데이터를 넣고 있다는 점에서 문제가 발생된 것입니다.
    이는 JPA 연관관계가 깨져 무시됩니다.  순수 SQL 이기에 @OneToMany, @ManyToOne, orphanRemoval, cascade 같은 JPA 설정은 전혀 적용되지 않는다.
    이를 해결하기 위한 가장 좋은 방법으로는 JAVA 코드로 더미 데이터를 넣어주는 것입니다.
    아래를 참고하면 되겠습니다.
    @Component
    @RequiredArgsConstructor
    public class RecipeDummyDataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final FoodRepository foodRepository;

    @Override
    public void run(String... args) throws Exception {
        // 1. Food 엔티티 먼저 조회 (예: 5번 "카레라이스")
        Food food = foodRepository.findById(5L)
                .orElseThrow(() -> new RuntimeException("Food not found"));

        // 2. Recipe 생성
        Recipe recipe = new Recipe();
        recipe.setDifficulty(Recipe.Difficulty.DIFFICULTY_LEVEL_ONE);
        recipe.setFood(food); // 연관관계 설정

        // 3. RecipeStep 생성 + 양방향 연결
        RecipeStep step1 = new RecipeStep(null, 1, "야채와 고기를 볶고 카레가루를 넣습니다.", "10분", recipe);
        RecipeStep step2 = new RecipeStep(null, 2, "물을 넣고 10분간 끓입니다.", "10분", recipe);

        recipe.setProcess(Arrays.asList(step1, step2));

        // 4. 저장
        recipeRepository.save(recipe);
        }
    }
     */
    public void deleteRecipe(long recipeId, long foodId) {
        Recipe recipe = findVerifiedRecipe(recipeId);

        if (recipe.getFood().getFoodId() != foodId) {
            throw new BusinessLogicException(ExceptionCode.FORBIDDEN);
        }

        // 명시적으로 자식 레퍼런스까지 끊자
        for (RecipeStep step : recipe.getProcess()) {
            step.setRecipe(null);  // ← 중요!!
        }

        // 자식 리스트 clear
        recipe.getProcess().clear();

        // 먼저 저장 (orphanRemoval 트리거)
        recipeRepository.save(recipe);

        // 부모 삭제
        recipeRepository.delete(recipe);
        recipeRepository.flush();
    }

    public Recipe findVerifiedRecipe(long recipeId) { //현재 존재하는지 확인하는 기능 find, update, delete
        Optional<Recipe> optionalRecipe = recipeRepository.findById(recipeId); //npe 발생 방지 optional 사용
        Recipe findRecipe = optionalRecipe.orElseThrow(() -> new BusinessLogicException(ExceptionCode.NOT_FOUND));
        return findRecipe;
    }


}
