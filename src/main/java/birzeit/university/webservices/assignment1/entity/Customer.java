package birzeit.university.webservices.assignment1.entity;

import java.io.Serializable;
import javax.persistence.*;
import org.apache.tomcat.jni.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   
@NoArgsConstructor
@Entity                 
@Table 

public class Customer implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column
    private String email;

    @Column
    private Long phoneNumber;

    @Column
    private Address address;
    
}
