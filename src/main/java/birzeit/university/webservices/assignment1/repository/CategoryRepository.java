package birzeit.university.webservices.assignment1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import birzeit.university.webservices.assignment1.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    
}
