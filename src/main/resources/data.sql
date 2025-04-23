-- 1. GoalType (건강 목표 종류)
INSERT INTO goal_type (goal_type_category, goal_type_name) VALUES
                                                               ('체중관련', '체중감소'),
                                                               ('체중관련', '체중유지'),
                                                               ('체중관련', '체중증가'),
                                                               ('성장/재활', '성장발달'),
                                                               ('성장/재활', '재활치료'),
                                                               ('영양/소화', '영양보충'),
                                                               ('영양/소화', '소화개선'),
                                                               ('면역/질병 예방', '면역력강화'),
                                                               ('면역/질병 예방', '질병예방');

-- 2. AgeGroup (연령대)
INSERT INTO age_group (age_group_name) VALUES
                                           ('유아'),
                                           ('어린이'),
                                           ('청소년'),
                                           ('성인'),
                                           ('고령자');

-- 3. Ingredient (식재료)
INSERT INTO ingredient (ingredient_id, ingredient_name, ingredient_type, ingredient_origin, expiry_date, storage_method)
VALUES
    (1, '고구마', '식량작물', '국내산', '2025-12-31', '상온보관'),
    (2, '닭가슴살', '축산물', '국내산', '2025-11-15', '냉장보관'),
    (3, '된장', '발효식품', '국내산', '2025-12-01', '냉장보관'),
    (4, '두부', '가공식품', '국내산', '2025-11-01', '냉장보관'),
    (5, '감자', '식량작물', '국내산', '2025-10-01', '상온보관');
-- 4. Member (회원)
INSERT INTO member (email, password, name, birthday, phone) VALUES
                                                                ('admin@gmail.com', '123', '관리자', '1999-12-25', '010-0000-0000'),
                                                                ('user1@gmail.com', '1234', '유저1', '2001-01-01', '010-1111-1111'),
                                                                ('user2@gmail.com', '12345', '유저2', '2002-02-02', '010-2222-2222');

-- 5. Member Roles
INSERT INTO member_roles (member_member_id, roles) VALUES
                                                       (1, 'ROLE_USER'),
                                                       (1, 'ROLE_ADMIN'),
                                                       (2, 'ROLE_USER'),
                                                       (3, 'ROLE_USER');

-- 6. Food (음식)
INSERT INTO food (food_id, food_name, food_image_url, food_create_date) VALUES
                                                                            (1, '닭가슴살 샐러드', '/foods/chicken-salad.jpg', '2025-04-14'),
                                                                            (2, '된장찌개', '/foods/soybean-stew.jpg', '2025-01-10'),
                                                                            (3, '고구마 구이', '/foods/sweetpotato.jpg', '2025-04-17');

-- 7. FoodIngredient
INSERT INTO food_ingredient (food_id, ingredient_id) VALUES
                                                         (1, 2),
                                                         (2, 3),
                                                         (2, 4),
                                                         (3, 1);

-- 8. AgeGroupFood
INSERT INTO age_group_food (age_group_id, food_id) VALUES
                                                       (3, 1),
                                                       (4, 2),
                                                       (5, 3);

-- 9. Target
INSERT INTO target (age_group_id, goal_type_id, target_status) VALUES
                                                                   (3, 1, 'TARGET_REGISTERED'),
                                                                   (4, 8, 'TARGET_REGISTERED'),
                                                                   (5, 4, 'TARGET_REGISTERED');

-- 10. MemberTarget
INSERT INTO member_target (member_id, target_id) VALUES
                                                     (1, 1),
                                                     (2, 2),
                                                     (3, 3);

-- 11. FoodRecommend
INSERT INTO food_recommend (food_id, goal_type_id, effect, reason) VALUES
                                                                       (1, 1, '체중감소', '고단백 샐러드'),
                                                                       (2, 8, '항산화 강화', '전통 발효 음식인 된장 사용'),
                                                                       (3, 4, '식욕증진', '식이섬유 풍부');

-- 12. FoodLike
INSERT INTO food_like (member_id, food_id, like_count, like_date) VALUES
                                                                      (1, 1, 1, '2025-04-01'),
                                                                      (2, 2, 400, '2025-01-10'),
                                                                      (3, 3, 600, '2025-04-17');

-- 13. FoodView
INSERT INTO food_view (member_id, food_id, food_view_count) VALUES
                                                                (1, 1, 123),
                                                                (2, 2, 444),
                                                                (3, 3, 222);

-- 14. IngredientAnalysis
INSERT INTO ingredient_analysis (ingredient_id, nutrient_name, amount, daily_percentage) VALUES
                                                                                             (1, '칼로리', '120kcal', NULL),
                                                                                             (1, '탄수화물', '40g', NULL),
                                                                                             (1, '단백질', '2g', NULL),
                                                                                             (1, '지방', '0g', NULL),
                                                                                             (1, '비타민', '10mg', NULL),
                                                                                             (1, '미네랄', '5mg', NULL),
                                                                                             (1, '수분', '70%', NULL),
                                                                                             (1, '섬유질', '3g', NULL),
                                                                                             (2, '칼로리', '280kcal', NULL),
                                                                                             (2, '탄수화물', '10g', NULL),
                                                                                             (2, '단백질', '30g', NULL),
                                                                                             (2, '지방', '5g', NULL),
                                                                                             (2, '비타민', '20mg', NULL),
                                                                                             (2, '미네랄', '15mg', NULL),
                                                                                             (2, '수분', '60%', NULL),
                                                                                             (2, '섬유질', '5g', NULL),
                                                                                             (3, '칼로리', '180kcal', NULL),
                                                                                             (3, '탄수화물', '15g', NULL),
                                                                                             (3, '단백질', '10g', NULL),
                                                                                             (3, '지방', '3g', NULL),
                                                                                             (3, '비타민', '12mg', NULL),
                                                                                             (3, '미네랄', '10mg', NULL),
                                                                                             (3, '수분', '80%', NULL),
                                                                                             (3, '섬유질', '2g', NULL);
-- 15. Recipe (food_id, difficulty, totalCookingTime)
INSERT INTO recipe (food_id, difficulty, total_cooking_time) VALUES
                                                                 (1, 'DIFFICULTY_LEVEL_ONE', '20분'),
                                                                 (2, 'DIFFICULTY_LEVEL_TWO', '60분'),
                                                                 (3, 'DIFFICULTY_LEVEL_ONE', '30분');
-- 16. RecipeProcess (recipe_id, step, instruction, cooktime)
INSERT INTO recipe_process (recipe_id, step, instruction, cooktime) VALUES
                                                                        -- 닭가슴살 샐러드
                                                                        (1, 1, '닭가슴살을 삶거나 구워서 잘게 찢는다.', 10),
                                                                        (1, 2, '야채를 깨끗이 씻어 먹기 좋은 크기로 자른다.', 5),
                                                                        (1, 3, '볼에 모든 재료를 담고 발사믹 소스를 뿌려 섞는다.', 5),

                                                                        -- 된장찌개
                                                                        (2, 1, '멸치로 육수를 끓인다.', 10),
                                                                        (2, 2, '모든 채소를 썰고 두부도 준비한다.', 20),
                                                                        (2, 3, '육수에 된장을 풀고 재료를 넣어 끓인다.', 30),

                                                                        -- 군고구마
                                                                        (3, 1, '고구마를 깨끗이 씻는다.', 10),
                                                                        (3, 2, '180도 오븐에서 30~40분간 굽는다.', 10),
                                                                        (3, 3, '노릇하게 익으면 꺼내서 먹는다.', 20);

-- 17. Review
INSERT INTO review (member_id, food_id, content, rating, review_create_date) VALUES
                                                                                 (2, 1, '정말 맛있고 건강한 느낌이에요.', 4, '2025-04-14'),
                                                                                 (3, 1, '건강한 식단에 딱 어울리는 메뉴입니다.', 3, '2024-11-18'),
                                                                                 (2, 2, '건강한 식단에 딱 어울리는 메뉴입니다.', 4, '2025-01-10'),
                                                                                 (3, 2, '간단하고 만들기 쉬웠어요!', 3, '2024-10-25'),
                                                                                 (1, 3, '간단하고 만들기 쉬웠어요!', 3, '2025-04-17'),
                                                                                 (2, 3, '소스만 조금 바꾸면 더 맛있을 것 같아요!', 5, '2025-02-07');

-- 18. IngredientMarket
INSERT INTO ingredient_market (ingredient_id, market_name, market_region, market_price, market_map) VALUES
                                                                                                        (1, '서울농수산시장', '서울특별시 송파구', 2500, 'https://maps.google.com/?q=서울농수산시장'),
                                                                                                        (2, '부산자갈치시장', '부산광역시 중구', 4200, 'https://maps.google.com/?q=부산자갈치시장'),
                                                                                                        (3, '대구칠성시장', '대구광역시 북구', 5500, 'https://maps.google.com/?q=대구칠성시장'),
                                                                                                        (4, '광주양동시장', '광주광역시 서구', 3800, 'https://maps.google.com/?q=광주양동시장'),
                                                                                                        (5, '대전중앙시장', '대전광역시 중구', 3100, 'https://maps.google.com/?q=대전중앙시장');
