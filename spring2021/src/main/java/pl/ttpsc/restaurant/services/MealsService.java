package pl.ttpsc.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.MealsRepository;
import pl.ttpsc.restaurant.services.converters.MealsConverter;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class MealsService {

    public static final String MEAL_NOT_FOUND_MSG = "Meal with id '%s' not found.";

    private final MealsRepository mealsRepository;

    @Autowired
    public MealsService(MealsRepository mealsRepository) {
        this.mealsRepository = mealsRepository;
    }

    public MealEntity addMeal(Meal meal) {
        return mealsRepository.save(MealsConverter.toEntity(meal));
    }

    public Meal getMeal(final long id) {
        MealEntity mealEntity =  mealsRepository.findById(id).orElseThrow(() -> new MealNotFoundException(String.format(MEAL_NOT_FOUND_MSG, id)));
        return MealsConverter.toModel(mealEntity);
    }

    public MealEntity updateMeal(long id, Meal meal) {
        MealEntity mealEntity = mealsRepository.findById(id).orElseThrow(() -> new MealNotFoundException(String.format(MEAL_NOT_FOUND_MSG, id)));
        mealEntity.setCost(meal.getCost());
        mealEntity.setDescription(meal.getDescription());
        mealEntity.setName(meal.getName());
        return mealsRepository.save(MealsConverter.toEntity(meal));
    }

    public List<Meal> getAllMeals() {
        return mealsRepository.findAll().stream().map(MealsConverter::toModel).collect(Collectors.toList());
    }

    public void deleteMeal(long id){
        if( mealsRepository.findById(id).isPresent()){
            MealEntity mealEntity = mealsRepository.findById(id).get();
            mealsRepository.delete(mealEntity);
        }
    }
}
