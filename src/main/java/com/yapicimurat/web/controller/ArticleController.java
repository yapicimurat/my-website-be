package com.yapicimurat.web.controller;

import com.yapicimurat.common.mapper.ArticleMapper;
import com.yapicimurat.common.mapper.PageableMapper;
import com.yapicimurat.dto.article.ArticleInputDTO;
import com.yapicimurat.dto.pageable.PageableDTO;
import com.yapicimurat.service.ArticleService;
import com.yapicimurat.web.controller.response.SuccessDataResponse;
import com.yapicimurat.web.input.article.ArticleInput;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.dto.article.ArticleDTO;
import com.yapicimurat.web.output.article.ArticleOutput;
import com.yapicimurat.web.output.pageable.PageableOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RequestMapping(path = "/api/article")
public class ArticleController {
    private final ArticleService articleService;

    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public ResponseEntity<DataResponse<PageableOutput<ArticleOutput>>> getAll(@RequestParam(value = "page", required = false) Integer currentPage) {
        PageableDTO<ArticleDTO> pageableDTO = articleService.getAll(currentPage);
        List<ArticleOutput> articleOutput = ArticleMapper.INSTANCE.convertArticleDTOListToArticleOutputList((List<ArticleDTO>) pageableDTO.elements());
        PageableOutput<ArticleOutput> pageableOutput = new PageableOutput<>(
                articleOutput,
                //TODO: en son burada kaldin
                //TODO: PageableDTO -> PagaeblaOutput donusturme yapamadin!!!
        )
        return ResponseEntity.ok(
                SuccessDataResponse.<PageableOutput<ArticleOutput>>createSuccessDataResponse(
                        ,
                        ""
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleOutput>> getById(@Valid @PathVariable("id") String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.convertArticleDTOToArticleOutput(articleService.getById(UUID.fromString(id)))
                        ,""
                )
        );
    }

    @PostMapping
    public ResponseEntity<DataResponse<ArticleOutput>> create(@Valid @RequestBody ArticleInput articleInput) {
        ArticleInputDTO articleInputDTO = ArticleMapper.INSTANCE.convertArticleInputToArticleInputDTO(articleInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.convertArticleDTOToArticleOutput(articleService.create(articleInputDTO))
                        ,""
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<ArticleOutput>> updateById(@PathVariable("id") String id,
                                                 @RequestBody ArticleInput articleInput) {
        ArticleInputDTO articleInputDTO = ArticleMapper.INSTANCE.convertArticleInputToArticleInputDTO(articleInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        ArticleMapper.INSTANCE.convertArticleDTOToArticleOutput(articleService.update(id, articleInputDTO))
                        ,""
                )
        );
    }

}
