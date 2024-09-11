package com.yapicimurat.service;

import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.dto.category.CategoryInputDTO;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CategoryService {
    List<CategoryDTO> getAllByIdIn(Set<UUID> categoryIds);
    List<CategoryDTO> getAll();
    CategoryDTO getById(UUID id) throws EntityNotFoundException;
    Optional<CategoryDTO> getOptionalCategoryDTOById(UUID id);
    boolean existsByName(String name);
    List<CategoryDTO> createAll(Set<CategoryInputDTO> categoryInputDTOSet);
    CategoryDTO create(CategoryInputDTO requestBody) throws EntityAlreadyExistsException;
    CategoryDTO updateById(UUID id, CategoryInputDTO requestBody) throws EntityNotFoundException;
}
