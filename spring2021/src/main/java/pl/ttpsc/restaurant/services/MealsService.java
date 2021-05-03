package pl.ttpsc.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.MealsRepository;

import java.util.List;

@Service
public class MealsService {

    public static final String MEAL_NOT_FOUND_MSG = "Meal with id '%s' not found.";

    private MealsRepository mealsRepository;

    @Autowired
    public MealsService(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    public MealEntity getMeal(long id) {
        return mealsRepository.findById(id).orElseThrow(() -> new MealNotFoundException(String.format(MEAL_NOT_FOUND_MSG, id)));
    }

    public MealEntity updateMeal(MealEntity meal) {
        MealEntity updatedMeal = mealsRepository.save(meal);
        return updatedMeal;
    }

    public MealEntity addMeal(MealEntity meal) {
        MealEntity createdMeal = mealsRepository.save(meal);
        return createdMeal;
    }

    public List<MealEntity> getAllMeals() {
        return mealsRepository.findAll();
    }
}
