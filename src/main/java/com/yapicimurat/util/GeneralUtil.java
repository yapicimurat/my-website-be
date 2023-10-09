package com.yapicimurat.util;

import com.yapicimurat.dto.BaseDTO;
import com.yapicimurat.model.abs.BaseModel;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public final class GeneralUtil {
    private GeneralUtil() {}

    public static boolean isNullOrEmpty(String str) {
        return str == null || str.isEmpty();
    }

    public static <T extends BaseDTO, S extends BaseModel> List<T> mapEntityListToDTOList(
                                                                                        List<S> sourceList,
                                                                                        Class<T> target,
                                                                                        ModelMapper modelMapper) {
        return sourceList.stream().map(source -> modelMapper.map(source, target)).collect(Collectors.toList());
    }
}
