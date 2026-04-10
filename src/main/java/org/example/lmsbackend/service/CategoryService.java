package org.example.lmsbackend.service;

import org.example.lmsbackend.dto.CategoryDetailDTO;
import org.example.lmsbackend.dto.CourseDTO;
import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.model.Courses;
import org.example.lmsbackend.repository.CategoryRepository;
import org.example.lmsbackend.repository.CourseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CourseRepository courseRepository;
    private final Categories categories;
    private final Courses courses;

    public CategoryService(CategoryRepository categoryRepository, CourseRepository courseRepository, Categories categories, Courses courses) {
        this.courseRepository = courseRepository;
        this.categoryRepository = categoryRepository;
        this.categories = categories;
        this.courses = courses;
    }

    public List<Categories> getAllCategories(){
        return categoryRepository.findAll();
    }

    public CategoryDetailDTO getCategory(Long id){

        Categories category = categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category id " + id + " is not found!!"));

        List<CourseDTO> dto = courseRepository.findByCategories_CategoryId(id)
                .stream()
                .map(courses->new CourseDTO(
                        courses.getCourseId(),
                        courses.getTitle(),
                        courses.getDescription(),
                        courses.getPrice(),
                        courses.getOverallDuration(),
                        courses.getCoverDir(),
                        courses.getInstructorId().getUsername()
                ))
                .toList();

        return new CategoryDetailDTO(
                category.getCategoryId(),
                category.getCategory(),
                dto
        );
    }

    public Categories addCategory(Categories category){
        System.out.println("Hello");
        return categoryRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories category){
        Categories existingCategory = new Categories();

        existingCategory.setCategoryId(id);
        existingCategory.setCategory(category.getCategory());

        existingCategory.setCategory(category.getCategory());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
