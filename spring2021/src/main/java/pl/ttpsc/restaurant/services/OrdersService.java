package pl.ttpsc.restaurant.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.ttpsc.restaurant.errors.MealNotFoundException;
import pl.ttpsc.restaurant.errors.OrderNotFoundException;
import pl.ttpsc.restaurant.model.Order;
import pl.ttpsc.restaurant.model.OrderResponse;
import pl.ttpsc.restaurant.persistance.MealsRepository;
import pl.ttpsc.restaurant.persistance.OrdersRepository;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;
import pl.ttpsc.restaurant.services.converters.OrdersConverter;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

import static pl.ttpsc.restaurant.services.MealsService.MEAL_NOT_FOUND_MSG;

@Service
public class OrdersService {

    private static final String ORDER_NOT_FOUND_MSG = "Order with id '%s' not found.";

    private OrdersRepository ordersRepository;
    private MealsRepository mealsRepository;

    @Autowired
    public OrdersService(OrdersRepository ordersRepository, MealsRepository mealsRepository) {
        this.ordersRepository = ordersRepository;
        this.mealsRepository = mealsRepository;
    }

    @Transactional
    public OrderEntity addOrder(final Order order) {
        //TODO to be implemented
        return null;
    }

    public OrderResponse getOrder(final long id) {
        final OrderEntity order = ordersRepository.findById(id)
                .orElseThrow(() -> new OrderNotFoundException(String.format(ORDER_NOT_FOUND_MSG, id)));

        final OrderResponse response = OrdersConverter.toResponse(order);
        return response;
    }
}
