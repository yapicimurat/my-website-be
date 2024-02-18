package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.CategoryCreateRequest;
import com.yapicimurat.controller.request.CategoryUpdateRequest;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Category;
import com.yapicimurat.repository.CategoryRepository;
import com.yapicimurat.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    public Set<Category> getAllByIdIn(Set<UUID> categoryIds) {
        return categoryRepository.findAllByIdIn(categoryIds);
    }

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(UUID id) throws EntityNotFoundException {
        return categoryRepository.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }
    @Override
    public Optional<Category> getByIdOptional(UUID id) {
        return categoryRepository.findById(id);
    }

    @Override
    public boolean existsByName(String name) {
        return categoryRepository.existsByName(name);
    }

    @Override
    public List<UUID> createAll(List<CategoryCreateRequest> requestBodyList) {
        final List<UUID> createdCategoryIdList = new ArrayList<>();

        requestBodyList.forEach(requestBody -> {
            if(!existsByName(requestBody.getName())) {
                final Category category = new Category();

                category.setName(requestBody.getName());
                category.setColor(requestBody.getColor());

                createdCategoryIdList.add(categoryRepository.save(category).id);
            }
        });

        return createdCategoryIdList;
    }

    @Override
    public UUID create(CategoryCreateRequest requestBody) throws EntityAlreadyExistsException {
        if(existsByName(requestBody.getName())) {
            throw new EntityAlreadyExistsException();
        }

        final Category category = new Category();
        category.setName(requestBody.getName());
        category.setColor(requestBody.getColor());

        return categoryRepository.save(category).id;
    }

    @Override
    public UUID updateById(UUID id, CategoryUpdateRequest requestBody) {
        final Category category = getById(id);

        if(existsByName(requestBody.getName())) {
            throw new EntityAlreadyExistsException();
        }

        category.setName(requestBody.getName());
        category.setColor(requestBody.getColor());

        return categoryRepository.save(category).id;
    }

}
