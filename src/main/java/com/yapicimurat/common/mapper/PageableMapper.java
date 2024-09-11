package com.yapicimurat.common.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PageableMapper {
    PageableMapper INSTANCE = Mappers.getMapper(PageableMapper.class);
}
