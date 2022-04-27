package birzeit.university.webservices.assignment1.Service.ServiceImpl;

import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

import birzeit.university.webservices.assignment1.Service.OrderService;
import birzeit.university.webservices.assignment1.dto.OrderDto;
import birzeit.university.webservices.assignment1.entity.Order;
import birzeit.university.webservices.assignment1.exception.ResourceNotFoundException;
import birzeit.university.webservices.assignment1.repository.OrderRepository;

public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public OrderDto createOrder(OrderDto orderDto) {

        Order order = mapToEntity(orderDto);
        Order newOrder = orderRepository.save(order);

        OrderDto orderResponse = mapToDTO(newOrder);
        return orderResponse;
    }

    @Override
    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(order -> mapToDTO(order)).collect(Collectors.toList());
    }

    @Override
    public OrderDto getOrderById(long id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        return mapToDTO(order);
    }

    @Override
    public OrderDto updateOrder(OrderDto orderDto, long id) {
        
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        order.setDate((Date) orderDto.getDate());
        order.setQuantity(orderDto.getQuantity());
        order.setCustomerId(orderDto.getCustomerId());
        order.setProductId(orderDto.getProductId());

        Order updatedOrder = orderRepository.save(order);
        return mapToDTO(updatedOrder);
    }

    @Override
    public void deleteOrderById(long id) {
        
        Order order = orderRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Order", "id", id));
        orderRepository.delete(order);
    }

    private OrderDto mapToDTO(Order order){
        OrderDto orderDto = new OrderDto();
        orderDto.setDate((Date) order.getDate());
        orderDto.setQuantity(order.getQuantity());
        orderDto.setCustomerId(order.getCustomerId());
        orderDto.setProductId(order.getProductId());
    
        return orderDto;
    }

    
    private Order mapToEntity(OrderDto orderDto){
        Order order = new Order();
        order.setDate((Date) orderDto.getDate());
        order.setQuantity(orderDto.getQuantity());
        order.setCustomerId(orderDto.getCustomerId());
        order.setProductId(orderDto.getProductId());
        
        return order;
    }
    
}
