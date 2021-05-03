package pl.ttpsc.restaurant.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.ttpsc.restaurant.persistance.entities.OrderEntity;

public interface OrdersRepository extends JpaRepository<OrderEntity, Long> {
}
