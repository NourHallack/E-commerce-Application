package birzeit.university.webservices.assignment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import birzeit.university.webservices.assignment1.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    
}




