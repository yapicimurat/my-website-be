package com.yapicimurat.controller;

import com.yapicimurat.controller.request.CategoryCreateRequest;
import com.yapicimurat.controller.request.CategoryUpdateRequest;
import com.yapicimurat.dto.CategoryDTO;
import com.yapicimurat.service.impl.CategoryFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/category")
public class CategoryController {
    private final CategoryFacade categoryFacade;

    public CategoryController(CategoryFacade categoryFacade) {
        this.categoryFacade = categoryFacade;
    }

    @GetMapping
    public ResponseEntity<List<CategoryDTO>> getAll() {
        return ResponseEntity.ok(categoryFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(categoryFacade.getById(id));
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody CategoryCreateRequest requestBody) {
        return ResponseEntity.ok(categoryFacade.create(requestBody));
    }

    @PostMapping("/create-all")
    public ResponseEntity<List<String>> createAll(@RequestBody List<CategoryCreateRequest> requestBody) {
        return ResponseEntity.ok(categoryFacade.createAll(requestBody));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateById(@PathVariable String id, @RequestBody CategoryUpdateRequest requestBody) {
        return ResponseEntity.ok(categoryFacade.updateById(id, requestBody));
    }
}