package com.yapicimurat.repository;

import com.yapicimurat.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    Set<Category> findAllByIdIn(Set<UUID> ids);
    boolean existsByName(String name);
    Set<Category> findAllById(UUID id);
}
