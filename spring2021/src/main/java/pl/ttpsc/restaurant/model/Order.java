package pl.ttpsc.restaurant.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Order {

    private String email;
    private Set<Long> meals;
    private long cost;
    private Status status = Status.IN_PROGRESS;
}
