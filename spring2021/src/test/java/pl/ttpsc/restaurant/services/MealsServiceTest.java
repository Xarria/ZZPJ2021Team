package pl.ttpsc.restaurant.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.persistance.MealsRepository;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;

import java.util.HashSet;
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
        MealEntity testMealEntity;
        testMealEntity = new MealEntity();
        testMealEntity.setCost(2115);
        testMealEntity.setName("TestName");
        testMealEntity.setId(2115);
        testMealEntity.setDescription("Description");
        MealEntity mealEntity = mealsService.getMeal(2115);
        testMealEntity.setOrders(set);
        mealsService.addMeal(testMealEntity);

        assertThat(mealEntity.getId()).isEqualTo(2115);
        assertThat(mealEntity.getDescription()).isEqualTo("Description");
        assertThat(mealEntity.getName()).isEqualTo("TestName");
        assertThat(mealEntity.getOrders()).isEqualTo(set);
        assertThat(mealEntity.getCost()).isEqualTo(0);
    }

    @Test
    public void testUpdateMeal() {
        MealEntity testMealEntity;
        testMealEntity = new MealEntity();
        testMealEntity.setCost(2115);
        testMealEntity.setName("TestName");
        testMealEntity.setId(2138);
        testMealEntity.setDescription("Description");
        MealEntity mealEntity = mealsService.getMeal(2138);

        testMealEntity.setName("Now it's spaghetti");
        mealsService.updateMeal(2138, testMealEntity);

        assertThat(mealsService.getMeal(2138).getName()).isEqualTo("Now it's spaghetti");
    }

    @Test
    public void testAddMeal() {
        MealEntity testMealEntity;
        testMealEntity = new MealEntity();
        testMealEntity.setCost(4848);
        testMealEntity.setName("TestName");
        testMealEntity.setId(2115);
        testMealEntity.setDescription("Description");
        MealEntity mealEntity = mealsService.getMeal(2115);
        testMealEntity.setOrders(set);


        Throwable result = Assertions.catchThrowable(() -> mealsService.getMeal(4848));
        assertThat(result).isInstanceOf(MealNotFoundException.class);
        assertThat(result.getMessage()).isEqualTo("Meal with id '4848' not found.");

        mealsService.addMeal(testMealEntity);

        MealEntity me = mealsService.getMeal(4848);

        assertThat(me.getId()).isEqualTo(4848);
        assertThat(me.getDescription()).isEqualTo("Description");
        assertThat(me.getName()).isEqualTo("TestName");
    }

    @Test
    public void testGetAllMeals() {
        int size = mealsService.getAllMeals().size();
        MealEntity m1 = new MealEntity();
        MealEntity m2 = new MealEntity();
        MealEntity m3 = new MealEntity();
        MealEntity m4 = new MealEntity();
        mealsService.addMeal(m1);
        mealsService.addMeal(m2);
        mealsService.addMeal(m3);
        mealsService.addMeal(m4);

        assertThat(mealsService.getAllMeals().size()).isEqualTo(size + 4);
    }
}