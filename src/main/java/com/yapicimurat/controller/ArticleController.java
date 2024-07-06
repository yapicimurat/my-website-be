package com.yapicimurat.controller;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.controller.response.DataResponse;
import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.dto.PageableDTO;
import com.yapicimurat.service.impl.facade.ArticleFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(path = "/api/article")
public class ArticleController {
    private final ArticleFacade articleFacade;

    public ArticleController(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }

    @GetMapping
    public ResponseEntity<DataResponse<PageableDTO<ArticleDTO>>> getAll(@RequestParam(value = "page", required = false) Integer currentPage) {
        return ResponseEntity.ok(articleFacade.getAll(currentPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleDTO>> getById(@Valid @PathVariable("id") String id) {
        return ResponseEntity.ok(articleFacade.getById(id));
    }

    @PostMapping
    public ResponseEntity<DataResponse<String>> create(@Valid @RequestBody ArticleCreateRequest requestBody) {
        return ResponseEntity.ok(articleFacade.create(requestBody));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleDTO>> updateById(@PathVariable("id") String id,
                                                 @RequestBody ArticleUpdateRequest requestBody) {
        return ResponseEntity.ok(articleFacade.updateById(id, requestBody));
    }

}
