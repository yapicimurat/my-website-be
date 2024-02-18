package com.yapicimurat.service;

import com.yapicimurat.controller.request.CategoryCreateRequest;
import com.yapicimurat.controller.request.CategoryUpdateRequest;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Category;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    Set<Category> getAllByIdIn(Set<UUID> categoryIds);
    List<Category> getAll();
    Category getById(UUID id) throws EntityNotFoundException;
    Optional<Category> getByIdOptional(UUID id);
    boolean existsByName(String name);
    List<UUID> createAll(List<CategoryCreateRequest> requestBodyList);
    UUID create(CategoryCreateRequest requestBody) throws EntityAlreadyExistsException;
    UUID updateById(UUID id, CategoryUpdateRequest requestBody);
}
