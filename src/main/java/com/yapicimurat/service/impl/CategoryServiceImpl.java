package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.CategoryCreateRequest;
import com.yapicimurat.controller.request.CategoryUpdateRequest;
import com.yapicimurat.exception.EntityAlreadyExistsException;
import com.yapicimurat.exception.EntityNotFoundException;
import com.yapicimurat.model.Category;
import com.yapicimurat.repository.CategoryRepository;
import com.yapicimurat.service.CategoryService;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getByIdList(final List<UUID> categoryIdList) {
        List<Category> categoryList = categoryRepository.findAllById(categoryIdList);

        return categoryList;
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
    public Optional<Category> getByName(String name) {
        return categoryRepository.getByName(name);
    }

    @Override
    public List<UUID> createAll(List<CategoryCreateRequest> requestBodyList) {
        final List<UUID> createdCategoryIdList = new ArrayList<>();

        requestBodyList.stream().forEach(requestBody -> {
            if(!getByName(requestBody.getName()).isPresent()) {
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
        if(getByName(requestBody.getName()).isPresent()) {
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

        if(getByName(requestBody.getName()).isPresent()) {
            throw new EntityAlreadyExistsException();
        }

        category.setName(requestBody.getName());
        category.setColor(requestBody.getColor());

        return categoryRepository.save(category).id;
    }

}
