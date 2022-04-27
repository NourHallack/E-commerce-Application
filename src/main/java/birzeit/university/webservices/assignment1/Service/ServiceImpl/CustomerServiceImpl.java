package birzeit.university.webservices.assignment1.Service.ServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

import birzeit.university.webservices.assignment1.Service.CustomerService;
import birzeit.university.webservices.assignment1.dto.CustomerDto;
import birzeit.university.webservices.assignment1.entity.Customer;
import birzeit.university.webservices.assignment1.exception.ResourceNotFoundException;
import birzeit.university.webservices.assignment1.repository.CustomerRepository;

public class CustomerServiceImpl implements CustomerService {

    private CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public CustomerDto createCustomer(CustomerDto customerDto) {

        Customer customer = mapToEntity(customerDto);
        Customer newCustomer = customerRepository.save(customer);

        CustomerDto customerResponse = mapToDTO(newCustomer);
        return customerResponse;
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<Customer> customers = customerRepository.findAll();
        return customers.stream().map(customer -> mapToDTO(customer)).collect(Collectors.toList());
    }

    @Override
    public CustomerDto getCustomerById(long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        return mapToDTO(customer);
    }

    @Override
    public CustomerDto updateCustomer(CustomerDto customerDto, long id) {
        
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());

        Customer updatedCustomer = customerRepository.save(customer);
        return mapToDTO(updatedCustomer);
    }

    @Override
    public void deleteCustomerById(long id) {
        
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
        customerRepository.delete(customer);
    }

    private CustomerDto mapToDTO(Customer customer){
        CustomerDto customerDto = new CustomerDto();
        customerDto.setName(customer.getName());
        customerDto.setEmail(customer.getEmail());
        customerDto.setPhoneNumber(customer.getPhoneNumber());
        customerDto.setAddress(customer.getAddress());
    
        return customerDto;
    }

    
    private Customer mapToEntity(CustomerDto customerDto){
        Customer customer = new Customer();
        customer.setName(customerDto.getName());
        customer.setEmail(customerDto.getEmail());
        customer.setPhoneNumber(customerDto.getPhoneNumber());
        customer.setAddress(customerDto.getAddress());
        
        return customer;
    }
    
}
