package birzeit.university.webservices.assignment1.entity;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data  
@AllArgsConstructor   
@NoArgsConstructor     
@Entity                 
@Table 

public class Order implements Serializable {

    @Id
    @GeneratedValue
    
    private Long id;
    private Long customerId;
    private Long productId;
    private int quantity;
    private Date date;
    
}
