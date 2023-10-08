package com.yapicimurat.repository;

import com.yapicimurat.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    @Query("SELECT category FROM Category as category WHERE  category.name = :name")
    Optional<Category> getByName(String name);

}
