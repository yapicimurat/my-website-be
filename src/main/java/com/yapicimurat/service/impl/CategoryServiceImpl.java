package com.yapicimurat.service.impl;

import com.yapicimurat.common.mapper.CategoryMapper;
import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.dto.category.CategoryInputDTO;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Category;
import com.yapicimurat.repository.CategoryRepository;
import com.yapicimurat.service.CategoryService;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDTO> getAllByIdIn(Set<UUID> categoryIds) {
        return CategoryMapper.INSTANCE
                .toCategoryDTOList(categoryRepository.findAllByIdIn(categoryIds));
    }

    @Override
    public List<CategoryDTO> getAll() {
        return CategoryMapper.INSTANCE
                .toCategoryDTOList(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO getById(UUID id) throws EntityNotFoundException {
        return categoryRepository.findById(id)
                .map(CategoryMapper.INSTANCE::toCategoryDTO)
                .orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public Optional<CategoryDTO> getOptionalCategoryDTOById(UUID id) {
        return categoryRepository.findById(id)
                .map(CategoryMapper.INSTANCE::toCategoryDTO);
    }

    @Override
    public boolean existsByName(final String name) {
        return categoryRepository.existsByNameIgnoreCase(name);
    }

    @Override
    public List<CategoryDTO> createAll(final Set<CategoryInputDTO> categoryInputDTOSet) {
        checkAtLeastOneNameExists(categoryInputDTOSet.stream().map(CategoryInputDTO::name).collect(Collectors.toUnmodifiableSet()));
        List<Category> categoryEntityList = CategoryMapper.INSTANCE
                .toCategoryList(categoryInputDTOSet);
        return CategoryMapper.INSTANCE
                .toCategoryDTOList(categoryRepository.saveAll(categoryEntityList));
    }

    @Override
    public CategoryDTO create(final CategoryInputDTO categoryInputDTO) throws EntityAlreadyExistsException {
        return saveCategory(null, categoryInputDTO);
    }

    @Override
    public CategoryDTO updateById(UUID id, CategoryInputDTO categoryInputDTO) {
        return saveCategory(id, categoryInputDTO);
    }

    private CategoryDTO saveCategory(@Nullable UUID id, CategoryInputDTO categoryInputDTO) {
        performPreChecksToSaveCategory(id, categoryInputDTO);
        final Category categoryToSave = CategoryMapper.INSTANCE.
                toCategory(categoryInputDTO);
        if(Objects.nonNull(id)) {
            categoryToSave.setId(id);
        }

        return CategoryMapper.INSTANCE
                .toCategoryDTO(categoryRepository.save(categoryToSave));
    }

    private void checkAtLeastOneNameExists(Set<String> nameSet) {
        if(categoryRepository.countByNameIn(nameSet) > 0) {
            throw new EntityAlreadyExistsException();
        }
    }

    private void performPreChecksToSaveCategory(@Nullable UUID id, CategoryInputDTO categoryInputDTO) {
        if(Objects.nonNull(id)) {
            if(categoryRepository.existsByIdNotAndNameIgnoreCase(id, categoryInputDTO.name())) {
                throw new EntityAlreadyExistsException();
            }
        }

        if(categoryRepository.existsByNameIgnoreCase(categoryInputDTO.name())) {
            throw new EntityAlreadyExistsException();
        }
    }

}
