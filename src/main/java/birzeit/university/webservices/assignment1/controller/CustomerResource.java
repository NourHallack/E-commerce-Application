package birzeit.university.webservices.assignment1.controller;

import birzeit.university.webservices.assignment1.dto.CustomerDto;
import birzeit.university.webservices.assignment1.exception.BadRequestException;
import birzeit.university.webservices.assignment1.Service.CustomerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/customer")
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);
    private CustomerService customerService; 

    public CustomerResource(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping
    public ResponseEntity<List<CustomerDto>> getAllCategories() {
        return ResponseEntity.ok().body(customerService.getAllCustomers()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(customerService.getCustomerById(id));
    }

    @PostMapping
    public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto) {
        if (customerDto.getId() != null) {
            log.error("Cannot have an ID {}", customerDto);
            throw new BadRequestException(CustomerResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@Valid @RequestBody CustomerDto customerDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(customerService.updateCustomer(customerDto, id), HttpStatus.OK);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCustomer(@PathVariable(name = "id") long id) {
        customerService.deleteCustomerById(id);

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
    
}
