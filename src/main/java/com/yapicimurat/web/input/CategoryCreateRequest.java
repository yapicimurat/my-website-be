package com.yapicimurat.web.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public record CategoryCreateRequest(
        @NotBlank
        @Size(min = 3, max = 100)
        String name,

        @NotBlank
        String color
) {
}
