package birzeit.university.webservices.assignment1.Service.ServiceImpl;

import birzeit.university.webservices.assignment1.Service.CategoryService;
import birzeit.university.webservices.assignment1.entity.Category;
import birzeit.university.webservices.assignment1.exception.ResourceNotFoundException;
import birzeit.university.webservices.assignment1.dto.CategoryDto;
import birzeit.university.webservices.assignment1.repository.CategoryRepository;


import java.util.List;
import java.util.stream.Collectors;

public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {

        // convert DTO to entity
        Category category = mapToEntity(categoryDto);
        Category newCategory = categoryRepository.save(category);

        // convert entity to DTO
        CategoryDto categoryResponse = mapToDTO(newCategory);
        return categoryResponse;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories.stream().map(category -> mapToDTO(category)).collect(Collectors.toList());
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        return mapToDTO(category);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, long id) {
        // get category by id from the database
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));

        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());

        Category updatedCategory = categoryRepository.save(category);
        return mapToDTO(updatedCategory);
    }

    @Override
    public void deleteCategoryById(long id) {
        // get category by id from the database
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
    }

    // convert Entity into DTO
    private CategoryDto mapToDTO(Category category){
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setId(category.getId());
        categoryDto.setName(category.getName());
        categoryDto.setDescription(category.getDescription());
    
        return categoryDto;
    }

    // convert DTO to entity
    private Category mapToEntity(CategoryDto categoryDto){
        Category category = new Category();
        category.setName(categoryDto.getName());
        category.setDescription(categoryDto.getDescription());
        
        return category;
    }

    

    
}
