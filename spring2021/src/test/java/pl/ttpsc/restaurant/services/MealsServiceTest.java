package pl.ttpsc.restaurant.services;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.persistance.MealsRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


class MealsServiceTest {

    private MealsService mealsService;
    private MealsRepository mealsRepository;

    @BeforeEach
    void setUp() {
        mealsRepository = Mockito.mock(MealsRepository.class);
        mealsService = new MealsService(mealsRepository);
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
}