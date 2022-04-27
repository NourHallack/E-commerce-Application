package birzeit.university.webservices.assignment1.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;

@Data
@AllArgsConstructor   
@NoArgsConstructor     
@Entity                 
@Table
public class Category implements Serializable{

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
}
