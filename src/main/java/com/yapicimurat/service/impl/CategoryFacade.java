package com.yapicimurat.service.impl;

import com.yapicimurat.controller.request.CategoryCreateRequest;
import com.yapicimurat.controller.request.CategoryUpdateRequest;
import com.yapicimurat.controller.response.DataResponse;
import com.yapicimurat.controller.response.SuccessDataResponse;
import com.yapicimurat.dto.CategoryDTO;
import com.yapicimurat.service.CategoryService;
import com.yapicimurat.util.ConverterUtil;
import com.yapicimurat.util.GeneralUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class CategoryFacade {

    private final CategoryService categoryService;
    private final ModelMapper modelMapper;

    public CategoryFacade(CategoryService categoryService, ModelMapper modelMapper) {
        this.categoryService = categoryService;
        this.modelMapper = modelMapper;
    }

    public DataResponse<List<CategoryDTO>> getByIdList(final List<String> categoryIdList) {
        final List<UUID> uuidList = ConverterUtil.stringListToUUIDList(categoryIdList);
        final List<CategoryDTO> categoryDTOList = GeneralUtil
                .mapEntityListToDTOList(categoryService.getByIdList(uuidList), CategoryDTO.class, modelMapper);

        return new SuccessDataResponse<>(categoryDTOList, "");
    }

    public DataResponse<List<CategoryDTO>> getAll() {
        final List<CategoryDTO> categoryDTOList = GeneralUtil
                .mapEntityListToDTOList(categoryService.getAll(), CategoryDTO.class, modelMapper);

        return new SuccessDataResponse<>(categoryDTOList, "");
    }

    public DataResponse<CategoryDTO> getById(String id) {
        final CategoryDTO categoryDTO = modelMapper.map(categoryService.getById(UUID.fromString(id)), CategoryDTO.class);

        return new SuccessDataResponse<>(categoryDTO, "");
    }

    public DataResponse<List<String>> createAll(List<CategoryCreateRequest> requestBody) {
        final List<String> createdCategoryIdList = ConverterUtil.uuidListToStringList(categoryService.createAll(requestBody));

        return new SuccessDataResponse<>(createdCategoryIdList, "");
    }

    public DataResponse<String> create(CategoryCreateRequest requestBody) {
        final String createdCategoryId = categoryService.create(requestBody).toString();

        return new SuccessDataResponse<>(createdCategoryId, "");
    }

    public DataResponse<String> updateById(String id, CategoryUpdateRequest requestBody) {
         final UUID updatedCategoryId = categoryService.updateById(UUID.fromString(id), requestBody);

         return new SuccessDataResponse<>(updatedCategoryId.toString(), "");
    }

}
