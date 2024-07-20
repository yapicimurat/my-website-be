package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.pageable.PageableDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PageableMapper {
    PageableMapper INSTANCE = Mappers.getMapper(PageableMapper.class);
    PageableDTO convertPageableDTOToPageableOutput(PageableDTO source);
}
