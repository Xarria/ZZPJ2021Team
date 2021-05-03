package pl.ttpsc.restaurant.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.persistance.MealsRepository;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;


class MealsServiceTest {

    private MealsService mealsService;
    private MealsRepository mealsRepository;
    private Set<OrderEntity> set;

    @BeforeEach
    void setUp() {
        mealsRepository = Mockito.mock(MealsRepository.class);
        mealsService = new MealsService(mealsRepository);
        OrderEntity o1 = new OrderEntity();
        o1.setEmail("testMail1@test.com");
        OrderEntity o2 = new OrderEntity();
        o2.setEmail("testMail1@test.com");
        set = new HashSet<>();
        set.add(o1);
        set.add(o2);
    }


    @Test
    public void shouldThrowNotFoundExceptionWhenNoResult(){
        //given
        Mockito.when(mealsRepository.findById(1L)).thenReturn(Optional.empty());

        //when
        Throwable result = Assertions.catchThrowable(() -> mealsService.getMeal(1));

        //then
        assertThat(result).isInstanceOf(MealNotFoundException.class);
        assertThat(result.getMessage()).isEqualTo("Meal with id '1' not found.");
    }

    @Test
    public void testGetMeal() {
        Meal testMeal = new Meal();
        testMeal.setCost(2115);
        testMeal.setName("TestName");
        testMeal.setDescription("Description");
        MealEntity me = mealsService.addMeal(testMeal);
        Meal meal = mealsService.getMeal(me.getId());

        assertThat(meal.getDescription()).isEqualTo("Description");
        assertThat(meal.getName()).isEqualTo("TestName");
        assertThat(meal.getCost()).isEqualTo(0);
    }

    @Test
    public void testUpdateMeal() {
        Meal testMeal = new Meal();
        testMeal.setCost(2115);
        testMeal.setName("TestName");
        testMeal.setDescription("Description");
        MealEntity me = mealsService.addMeal(testMeal);
        Meal meal = mealsService.getMeal(me.getId());

        meal.setName("Now it's spaghetti");
        mealsService.updateMeal(me.getId(), meal);

        assertThat(mealsService.getMeal(me.getId()).getName()).isEqualTo("Now it's spaghetti");
    }

    @Test
    public void testAddMeal() {
        Meal testMeal = new Meal();

        int size = mealsService.getAllMeals().size();


        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);

        assertThat(size + 4).isEqualTo(mealsService.getAllMeals().size());
    }

    @Test
    public void testGetAllMeals() {
        Meal testMeal = new Meal();

        int size = mealsService.getAllMeals().size();

        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);
        mealsService.addMeal(testMeal);

        assertThat(size + 4).isEqualTo(mealsService.getAllMeals().size());
    }
}