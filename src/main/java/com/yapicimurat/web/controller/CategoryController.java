package com.yapicimurat.web.controller;

import com.yapicimurat.common.mapper.CategoryMapper;
import com.yapicimurat.dto.category.CategoryInputDTO;
import com.yapicimurat.service.CategoryService;
import com.yapicimurat.web.controller.response.SuccessDataResponse;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.web.input.category.CategoryInput;
import com.yapicimurat.web.output.category.CategoryOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryService categoryService;
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<CategoryOutput>>> getAll() {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CategoryMapper.INSTANCE.convertCategoryDTOListToCategoryOutputList(categoryService.getAll()),
                        ""
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CategoryOutput>> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CategoryMapper.INSTANCE
                                .convertCategoryDTOToCategoryOutput(categoryService.getById(UUID.fromString(id))),
                        ""
                )
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<CategoryOutput>> create(@Valid @RequestBody CategoryInput categoryInput) {
        CategoryInputDTO categoryInputDTO = CategoryMapper.INSTANCE.convertCategoryInputToCategoryInputDTO(categoryInput);

        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CategoryMapper.INSTANCE
                                .convertCategoryDTOToCategoryOutput(categoryService.create(categoryInputDTO)),
                        ""
                )
        );
    }

    @PostMapping("/create-all")
    public ResponseEntity<DataResponse<List<CategoryOutput>>> createAll(@RequestBody Set<CategoryInput> categoryInputSet) {
        Set<CategoryInputDTO> categoryInputDTOSet = CategoryMapper.INSTANCE
                .convertCategoryInputSetToCategoryInputDTOSet(categoryInputSet);

        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CategoryMapper.INSTANCE
                                .convertCategoryDTOListToCategoryOutputList(categoryService.createAll(categoryInputDTOSet)),
                        ""
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CategoryOutput>> updateById(@PathVariable("id") String id, @RequestBody CategoryInput categoryInput) {
        CategoryInputDTO categoryInputDTO = CategoryMapper.INSTANCE
                .convertCategoryInputToCategoryInputDTO(categoryInput);

        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CategoryMapper.INSTANCE
                                .convertCategoryDTOToCategoryOutput(categoryService.updateById(
                                        UUID.fromString(id),
                                        categoryInputDTO
                                )),
                        ""
                )
        );
    }
}