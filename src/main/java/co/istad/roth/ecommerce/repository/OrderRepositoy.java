package co.istad.roth.ecommerce.repository;

import co.istad.roth.ecommerce.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface OrderRepositoy extends JpaRepository<Order, UUID> {

}
