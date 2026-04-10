package org.example.lmsbackend.repository;

import org.example.lmsbackend.model.Categories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Categories, Long> {

}
