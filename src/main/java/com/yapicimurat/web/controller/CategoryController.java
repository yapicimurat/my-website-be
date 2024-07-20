package com.yapicimurat.web.controller;

import com.yapicimurat.web.input.CategoryCreateRequest;
import com.yapicimurat.web.input.CategoryUpdateRequest;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.dto.category.CategoryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {

    @GetMapping
    public ResponseEntity<DataResponse<List<CategoryDTO>>> getAll() {
        //return ResponseEntity.ok(categoryFacade.getAll());
        return null;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CategoryDTO>> getById(@PathVariable("id") String id) {
        //return ResponseEntity.ok(categoryFacade.getById(id));
        return null;
    }

    @PostMapping
    public ResponseEntity<DataResponse<String>> create(@Valid @RequestBody CategoryCreateRequest requestBody) {
        //return ResponseEntity.ok(categoryFacade.create(requestBody));
        return null;
    }

    @PostMapping("/create-all")
    public ResponseEntity<DataResponse<List<String>>> createAll(@RequestBody List<CategoryCreateRequest> requestBody) {
        //return ResponseEntity.ok(categoryFacade.createAll(requestBody));
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<String>> updateById(@PathVariable("id") String id, @RequestBody CategoryUpdateRequest requestBody) {
        //return ResponseEntity.ok(categoryFacade.updateById(id, requestBody));
        return null;
    }
}