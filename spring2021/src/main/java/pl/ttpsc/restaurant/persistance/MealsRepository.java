package pl.ttpsc.restaurant.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;

@Repository
public interface MealsRepository extends JpaRepository<MealEntity, Long> {
}
