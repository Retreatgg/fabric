package org.example.tkani.repository;

import org.example.tkani.model.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {

    @Query("SELECT s FROM Subcategory s WHERE s.category.id = ?1")
    List<Subcategory> findAllByCategoryId(Long categoryId);

}