package com.yapicimurat.controller;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
import com.yapicimurat.controller.response.DataResponse;
import com.yapicimurat.dto.ArticleDTO;
import com.yapicimurat.service.impl.ArticleFacade;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping(path = "/api/article")
public class ArticleController {
    private final ArticleFacade articleFacade;

    public ArticleController(ArticleFacade articleFacade) {
        this.articleFacade = articleFacade;
    }

    @GetMapping
    public ResponseEntity<DataResponse<List<ArticleDTO>>> getAll() {
        return ResponseEntity.ok(articleFacade.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleDTO>> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(articleFacade.getById(id));
    }

    @PostMapping
    public ResponseEntity<DataResponse<String>> create(@RequestBody ArticleCreateRequest requestBody) {
        return ResponseEntity.ok(articleFacade.create(requestBody));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleDTO>> updateById(@PathVariable("id") String id,
                                                 @RequestBody ArticleUpdateRequest requestBody) {
        return ResponseEntity.ok(articleFacade.updateById(id, requestBody));
    }

}
