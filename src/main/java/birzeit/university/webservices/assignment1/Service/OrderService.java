package birzeit.university.webservices.assignment1.Service;

import java.util.List;

import birzeit.university.webservices.assignment1.dto.OrderDto;

public interface OrderService {

    OrderDto createOrder(OrderDto OrderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(long id);

    OrderDto updateOrder(OrderDto OrderDto, long id);

    void deleteOrderById(long id);
    
}
