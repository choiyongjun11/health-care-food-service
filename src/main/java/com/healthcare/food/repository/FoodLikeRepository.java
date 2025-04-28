    package com.healthcare.food.repository;

    import com.healthcare.food.entity.Food;
    import com.healthcare.food.entity.FoodLike;
    import com.healthcare.member.entity.Member;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.CrudRepository;
    import org.springframework.data.repository.query.Param;

    import java.util.Optional;

    public interface FoodLikeRepository extends JpaRepository<FoodLike, Long> {
        @Query("SELECT COALESCE(SUM(f.likeCount), 0) FROM FoodLike f WHERE f.food = :food")
        int sumLikeCountByFood(@Param("food") Food food);
        boolean existsByMember_MemberIdAndFood(Long memberId, Food food); // (liked 조회용)
        Optional<FoodLike> findByMemberAndFood(Member member, Food food);
    }
