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
    Category toCategory(CategoryInputDTO source);
    CategoryDTO toCategoryDTO(Category source);
    CategoryOutput toCategoryOutput(CategoryDTO source);
    CategoryInputDTO toCategoryInputDTO(CategoryInput source);
    List<Category> toCategoryList(List<CategoryDTO> source);
    List<Category> toCategoryList(Set<CategoryInputDTO> source);
    List<CategoryDTO> toCategoryDTOList(List<Category> categoryList);
    List<CategoryOutput> toCategoryOutputList(List<CategoryDTO> categoryDTOList);
    Set<CategoryInputDTO> toCategoryInputDTOSet(Set<CategoryInput> categoryInputSet);
    Set<CategoryDTO> toCategoryDTOSet(Set<Category> categorySet);
}
