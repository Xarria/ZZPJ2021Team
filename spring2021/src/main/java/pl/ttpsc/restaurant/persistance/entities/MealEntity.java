package pl.ttpsc.restaurant.persistance.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meals")
@EqualsAndHashCode(exclude = "orders")
public class MealEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private long id;
    private String name;
    private String description;
    private int cost;
    @ManyToMany(mappedBy = "meals")
    private Set<OrderEntity> orders = new HashSet<>();

}
