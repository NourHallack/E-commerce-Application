package birzeit.university.webservices.assignment1.controller;

import birzeit.university.webservices.assignment1.dto.OrderDto;
import birzeit.university.webservices.assignment1.exception.BadRequestException;
import birzeit.university.webservices.assignment1.Service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/orders")
public class OrderResource {

    private final Logger log = LoggerFactory.getLogger(OrderResource.class);
    private OrderService orderService; 

    public OrderResource(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllCategories() {
        return ResponseEntity.ok().body(orderService.getAllOrders()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@Valid @RequestBody OrderDto orderDto) {
        if (orderDto.getId() != null) {
            log.error("Cannot have an ID {}", orderDto);
            throw new BadRequestException(OrderResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(orderService.createOrder(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(@Valid @RequestBody OrderDto orderDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(orderService.updateOrder(orderDto, id), HttpStatus.OK);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable(name = "id") long id) {
        orderService.deleteOrderById(id);

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
    
}
