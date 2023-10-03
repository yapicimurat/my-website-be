package com.yapicimurat.controller;

import com.yapicimurat.controller.request.ArticleCreateRequest;
import com.yapicimurat.controller.request.ArticleUpdateRequest;
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
    public ResponseEntity<List<ArticleDTO>> getAll() {
        final List<ArticleDTO> articleDTOList = articleFacade.getAll();

        return ResponseEntity.ok(articleDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleDTO> getById(@PathVariable("id") String id) {
        final ArticleDTO articleDTO = articleFacade.getById(id);

        return ResponseEntity.ok(articleDTO);
    }

    @PostMapping
    public ResponseEntity<String> create(@RequestBody ArticleCreateRequest requestBody) {
        final String articleId = articleFacade.create(requestBody);

        return ResponseEntity.ok(articleId);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> updateById(@PathVariable("id") String id,
                                                 @RequestBody ArticleUpdateRequest requestBody) {
        final ArticleDTO articleId = articleFacade.updateById(id, requestBody);

        return ResponseEntity.ok(articleId);
    }

}
