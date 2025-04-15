package com.yapicimurat.repository;

import com.yapicimurat.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface CategoryRepository extends JpaRepository<Category, UUID> {
    List<Category> findAllByIdIn(Set<UUID> ids);
    boolean existsByNameIgnoreCase(String name);
    boolean existsByIdNotAndNameIgnoreCase(UUID id, String name);
    long countByNameIn(Set<String> nameSet);
}
