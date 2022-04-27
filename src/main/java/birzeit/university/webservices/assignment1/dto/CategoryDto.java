package birzeit.university.webservices.assignment1.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor   
@NoArgsConstructor 

public class CategoryDto {

    private Long id;
    private String name;
    private String description;
    
}
