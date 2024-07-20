package com.yapicimurat.web.output.pageable;

import java.util.Collection;

public record PageableOutput<T>(
        Collection<T> elements,
        int totalPages,
        long totalElementsPerPage,
        int currentPage,
        boolean hasNext,
        boolean hasPrevious
) {
}
