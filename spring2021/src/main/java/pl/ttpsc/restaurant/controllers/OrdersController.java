package pl.ttpsc.restaurant.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ttpsc.restaurant.model.Order;
import pl.ttpsc.restaurant.model.OrderResponse;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;
import pl.ttpsc.restaurant.services.OrdersService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class OrdersController {

    private final OrdersService ordersService;

    @Autowired
    public OrdersController(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    @PostMapping(path = "/orders", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<URI> addOrder(@RequestBody final Order order) {
        final OrderEntity createdOrder = ordersService.addOrder(order);
        return ResponseEntity.created(URI.create("/orders/" + createdOrder.getId())).build();
    }

    @GetMapping(path = "/orders/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> getOrder(@PathVariable final long id) {
        final OrderResponse response = ordersService.getOrder(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/orders", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponse>> getAllMeals() {
        final List<OrderResponse> meals = ordersService.getAllOrders();
        return ResponseEntity.ok(meals);
    }

    @PutMapping(path = "/orders/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<OrderResponse> update(@PathVariable long id, @RequestBody Order order) {
        final OrderEntity updatedOrder = ordersService.updateOrder(id, order);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/orders/" + updatedOrder.getId()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
    }
}
