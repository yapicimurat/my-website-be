package com.yapicimurat.web.output.pageable;

import com.yapicimurat.dto.pageable.PageableDTO;

import java.util.Collection;

public record PageableOutput<T>(
        Collection<T> elements,
        int totalPages,
        long totalElementsPerPage,
        int currentPage,
        boolean hasNext,
        boolean hasPrevious
) {

    public PageableOutput(PageableDTO pageableDTO) {
        this(
                pageableDTO.elements(),
                pageableDTO.totalPages(),
                pageableDTO.totalElementsPerPage(),
                pageableDTO.currentPage(),
                pageableDTO.hasNext(),
                pageableDTO.hasPrevious()
        );
    }
}
