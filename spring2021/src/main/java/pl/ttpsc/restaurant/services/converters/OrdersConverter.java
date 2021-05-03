package pl.ttpsc.restaurant.services.converters;

import org.springframework.beans.BeanUtils;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.model.Order;
import pl.ttpsc.restaurant.model.OrderResponse;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;

import java.util.HashSet;
import java.util.Set;

public class OrdersConverter {

    public static OrderEntity toEntity(Order order){
        OrderEntity orderEntity = new OrderEntity();
        BeanUtils.copyProperties(order, orderEntity);
        return orderEntity;
    }

    public static OrderResponse toResponse(OrderEntity order) {
        //TODO to be implemented
        return null;
    }
}
