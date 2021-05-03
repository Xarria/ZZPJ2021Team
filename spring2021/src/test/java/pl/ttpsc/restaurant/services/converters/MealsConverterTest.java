package pl.ttpsc.restaurant.services.converters;

import junit.framework.TestCase;
import org.springframework.beans.BeanUtils;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;

public class MealsConverterTest extends TestCase {

    public void testToModel() {
        MealEntity mealEntity = new MealEntity();
        mealEntity.setCost(2115);
        mealEntity.setDescription("Description");
        mealEntity.setName("Name");

        Meal meal = MealsConverter.toModel(mealEntity);

        assertEquals(mealEntity.getCost(), meal.getCost());
        assertEquals(mealEntity.getDescription(), meal.getDescription());
        assertEquals(mealEntity.getName(), meal.getName());
    }
}