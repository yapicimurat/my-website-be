package com.yapicimurat.web.controller;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.web.controller.response.SuccessDataResponse;
import com.yapicimurat.web.input.article.ArticleInput;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.web.output.article.ArticleDetailOutput;
import com.yapicimurat.web.output.article.ArticleOutput;
import com.yapicimurat.web.output.article.ArticleSummaryOutput;
import com.yapicimurat.web.output.pageable.PageableOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(path = "/api/article")
@Validated
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<DataResponse<PageableOutput<ArticleSummaryOutput>>> getAll(@RequestParam(value = "page", required = false) Integer currentPage) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        new PageableOutput<>(articleService.getAll(currentPage)),
                        ""
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleDetailOutput>> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.toArticleDetailOutput(articleService.getArticleDetailById(UUID.fromString(id)))
                        ,""
                )
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<ArticleOutput>> create(@Valid @RequestBody ArticleInput articleInput) {
        ArticleInputDTO articleInputDTO = ArticleMapper.INSTANCE.toArticleInputDTO(articleInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.toArticleOutput(articleService.create(articleInputDTO))
                        ,""
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleOutput>> updateById(@PathVariable("id") String id,
                                                 @RequestBody ArticleInput articleInput) {
        ArticleInputDTO articleInputDTO = ArticleMapper.INSTANCE.toArticleInputDTO(articleInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.toArticleOutput(articleService.update(id, articleInputDTO))
                        ,""
                )
        );
    }

}
