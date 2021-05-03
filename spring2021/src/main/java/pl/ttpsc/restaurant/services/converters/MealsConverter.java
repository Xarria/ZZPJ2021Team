package pl.ttpsc.restaurant.services.converters;

import org.springframework.beans.BeanUtils;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;

public class MealsConverter {
    public static Meal toModel(MealEntity mealEntity) {
        final Meal meal = new Meal();
        BeanUtils.copyProperties(mealEntity, meal);
        return meal;
    }
}
