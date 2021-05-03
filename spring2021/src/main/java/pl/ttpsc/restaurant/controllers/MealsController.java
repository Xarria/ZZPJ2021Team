package pl.ttpsc.restaurant.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.ttpsc.restaurant.model.Meal;
import pl.ttpsc.restaurant.persistance.entities.MealEntity;
import pl.ttpsc.restaurant.services.MealsService;

import java.net.URI;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class MealsController {

    @Autowired
    private MealsService mealsService;

    @GetMapping(path = "/meals", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getAllMeals(){
        final List<Meal> meals = mealsService.getAllMeals();
        return ResponseEntity.ok(meals);
    }

    @GetMapping(path = "/meals/{id}", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity getMeal(@PathVariable long id){
        final Meal meal = mealsService.getMeal(id);
        return ResponseEntity.ok(meal);
    }

    @PostMapping(path = "/meals", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity addMeal(@RequestBody Meal meal){
        final MealEntity createdMeal = mealsService.addMeal(meal);
        return ResponseEntity.created(URI.create("/meals/" + createdMeal.getId())).build();
    }

    @PutMapping(path = "/meals/{id}", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity update(@PathVariable long id, @RequestBody Meal meal){
        final MealEntity updatedMeal = mealsService.updateMeal(id, meal);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(URI.create("/meals/" + updatedMeal.getId()));
        return ResponseEntity.status(HttpStatus.NO_CONTENT).headers(headers).build();
    }
}
