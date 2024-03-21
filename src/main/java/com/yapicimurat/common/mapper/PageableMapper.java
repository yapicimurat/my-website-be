package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.Pageable;
import com.yapicimurat.dto.PageableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PageableMapper {
    PageableMapper INSTANCE = Mappers.getMapper(PageableMapper.class);

    PageableDTO convertPageableToPageableDTO(Pageable source);
}
