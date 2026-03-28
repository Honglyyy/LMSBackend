package org.example.lmsbackend.service;

import org.example.lmsbackend.model.Categories;
import org.example.lmsbackend.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Categories> getAllCategories(){
        return categoryRepository.findAll();
    }

    public Categories getCategory(Long id){
        return categoryRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Category id " + id + " is not found!!"));
    }

    public Categories addCategory(Categories category){
        System.out.println("Hello");
        return categoryRepository.save(category);
    }

    public Categories updateCategory(Long id, Categories category){
        Categories existingCategory = getCategory(id);

        existingCategory.setCategory(category.getCategory());

        return categoryRepository.save(existingCategory);
    }

    public void deleteCategory(Long id){
        categoryRepository.deleteById(id);
    }
}
