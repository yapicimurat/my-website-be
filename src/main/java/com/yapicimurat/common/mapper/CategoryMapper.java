package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.category.CategoryDTO;
import com.yapicimurat.dto.category.CategoryInputDTO;
import com.yapicimurat.model.Category;
import com.yapicimurat.web.input.category.CategoryInput;
import com.yapicimurat.web.output.category.CategoryOutput;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface CategoryMapper {
    CategoryMapper INSTANCE = Mappers.getMapper(CategoryMapper.class);
    Category convertCategoryInputDTOToCategoryEntity(CategoryInputDTO source);
    CategoryDTO convertCategoryEntityToCategoryDTO(Category source);
    CategoryOutput convertCategoryDTOToCategoryOutput(CategoryDTO source);
    CategoryInputDTO convertCategoryInputToCategoryInputDTO(CategoryInput source);
    List<Category> convertCategoryDTOListToCategoryEntityList(List<CategoryDTO> source);
    List<Category> convertCategoryInputDTOSetToCategoryEntityList(Set<CategoryInputDTO> source);
    List<CategoryDTO> convertCategoryEntityListToCategoryDTOList(List<Category> categoryList);
    List<CategoryOutput> convertCategoryDTOListToCategoryOutputList(List<CategoryDTO> categoryDTOList);
    Set<CategoryInputDTO> convertCategoryInputSetToCategoryInputDTOSet(Set<CategoryInput> categoryInputSet);
}
