package pl.ttpsc.restaurant.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pl.ttpsc.restaurant.RestaurantApplication;
import pl.ttpsc.restaurant.model.Order;
import pl.ttpsc.restaurant.model.OrderResponse;
import pl.ttpsc.restaurant.persistance.MealsRepository;
import pl.ttpsc.restaurant.persistance.OrdersRepository;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;

import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = {RestaurantApplication.class})
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application-integration.properties")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class OrdersApiIT {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mvc;

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private MealsRepository mealsRepository;

    @Before
    public void setUp() {
        ordersRepository.deleteAllInBatch();
        mealsRepository.deleteAllInBatch();

        assertThat(ordersRepository.count()).isEqualTo(0);
        assertThat(mealsRepository.count()).isEqualTo(0);
    }

    @Test
    public void shouldAddOrder() throws Exception {
        //given
        MealEntity created = mealsRepository.save(new MealEntity(1, "Meal1", "description", 15, null));
        assertThat(mealsRepository.count()).isEqualTo(1);

        Order requestBody = new Order("name@ttpsc.pl", Stream.of(1L).collect(Collectors.toSet()), 0);

        //when
        mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestBody))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        //then
        assertThat(ordersRepository.count()).isEqualTo(1);
    }

    @Test
    public void shouldReturnProperCostOfOrder() throws Exception {
        //given
        mealsRepository.save(new MealEntity(1, "Meal1", "description", 15, null));
        mealsRepository.save(new MealEntity(2, "Meal2", "description", 20, null));
        assertThat(mealsRepository.count()).isEqualTo(2);

        Order requestBody = new Order("name@ttpsc.pl", Stream.of(1L, 2L).collect(Collectors.toSet()), 0);

        //when
        MvcResult result = mvc.perform(post("/orders")
                .contentType(MediaType.APPLICATION_JSON).content(asJsonString(requestBody))
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated()).andReturn();

        String resultPath = result.getResponse().getHeader("Location");
        MvcResult orderResult = mvc.perform(get(resultPath)).andExpect(status().isOk()).andReturn();

        //then
        OrderResponse response = asObject(orderResult.getResponse().getContentAsByteArray(), OrderResponse.class);
        assertThat(response.getCost()).isEqualTo(35);
    }

    public String asJsonString(final Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T extends Object> T asObject(final byte[] content, final Class<T> clazz) throws IOException {
        return objectMapper.readValue(content, clazz);
    }
}
