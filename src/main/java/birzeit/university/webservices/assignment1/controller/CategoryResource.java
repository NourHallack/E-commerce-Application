package birzeit.university.webservices.assignment1.controller;


import birzeit.university.webservices.assignment1.dto.CategoryDto;
import birzeit.university.webservices.assignment1.exception.BadRequestException;
import birzeit.university.webservices.assignment1.Service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;



@RestController
@RequestMapping("/api/categories")
public class CategoryResource {
    
    private final Logger log = LoggerFactory.getLogger(CategoryResource.class);
    private CategoryService categoryService; 

    public CategoryResource(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAllCategories() {
        return ResponseEntity.ok().body(categoryService.getAllCategories()); 
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDto> getCategoryById(@PathVariable(name = "id") long id) {
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        if (categoryDto.getId() != null) {
            log.error("Cannot have an ID {}", categoryDto);
            throw new BadRequestException(CategoryResource.class.getSimpleName(),
                    "Id");
        }
        return new ResponseEntity<>(categoryService.createCategory(categoryDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto
            , @PathVariable(name = "id") long id) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryDto, id), HttpStatus.OK);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable(name = "id") long id) {
        categoryService.deleteCategoryById(id);

        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}

