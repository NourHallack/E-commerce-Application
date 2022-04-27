package birzeit.university.webservices.assignment1.dto;


import org.apache.tomcat.jni.Address;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   
@NoArgsConstructor 
public class CustomerDto {

    private Long id;
    private String name;
    private String email;
    private Long phoneNumber;
    private Address address;
    
}
