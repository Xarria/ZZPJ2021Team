package pl.ttpsc.restaurant.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String email;
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "orders_meals",
            joinColumns = {
                    @JoinColumn(name = "order_id", referencedColumnName = "id",
                            nullable = false, updatable = false)
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "meal_id", referencedColumnName = "id",
                            nullable = false, updatable = false)
            }
    )
    private Set<MealEntity> meals = new HashSet<>();
}
