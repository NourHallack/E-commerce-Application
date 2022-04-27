package birzeit.university.webservices.assignment1.dto;

import java.util.Date;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   
@NoArgsConstructor 
public class OrderDto {

    
    private Long id;
    private Long customerId;
    private Long productId;
    private int quantity;
    private Date date;
    
}
