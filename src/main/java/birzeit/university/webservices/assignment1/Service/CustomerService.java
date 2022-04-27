package birzeit.university.webservices.assignment1.Service;

import java.util.List;

import birzeit.university.webservices.assignment1.dto.CustomerDto;

public interface CustomerService {

    CustomerDto createCustomer(CustomerDto CustomerDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto getCustomerById(long id);

    CustomerDto updateCustomer(CustomerDto CustomerDto, long id);

    void deleteCustomerById(long id);
    
}
