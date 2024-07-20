package com.yapicimurat.dto.pageable;

import java.util.Collection;

public record PageableDTO<T>(
        Collection<T> elements,
        int totalPages,
        long totalElementsPerPage,
        int currentPage,
        boolean hasNext,
        boolean hasPrevious
) {
}
