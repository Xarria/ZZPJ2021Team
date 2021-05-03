package pl.ttpsc.restaurant.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ttpsc.restaurant.model.Order;
import pl.ttpsc.restaurant.model.OrderResponse;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;
import pl.ttpsc.restaurant.services.OrdersService;

import java.net.URI;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping(path = "/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addOrder(@RequestBody final Order order) {
        final OrderEntity createdOrder = ordersService.addOrder(order);
        return ResponseEntity.created(URI.create("/orders/" + createdOrder.getId())).build();
    }

    @GetMapping(path = "/orders/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getOrder(@PathVariable final long id) {
        final OrderResponse response = ordersService.getOrder(id);
        return ResponseEntity.ok(response);
    }

}
