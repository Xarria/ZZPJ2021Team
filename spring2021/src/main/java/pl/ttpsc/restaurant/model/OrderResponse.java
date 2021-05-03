package pl.ttpsc.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderResponse {

    private String email;
    private Set<Meal> meals;
    private long cost;
    private String status;
}
