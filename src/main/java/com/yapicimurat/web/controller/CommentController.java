package com.yapicimurat.web.controller;

import com.yapicimurat.web.input.CommentCreateRequest;
import com.yapicimurat.web.input.CommentUpdateRequest;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.dto.CommentDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
public class CommentController {
    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CommentDTO>> getById(@PathVariable("id") String id) {
        //return ResponseEntity.ok(commentFacade.getById(id));
        return null;
    }

    @GetMapping("/article/{articleId}/comment")
    public ResponseEntity<DataResponse<List<CommentDTO>>> getByArticleId(@PathVariable("articleId") String articleId) {
        //return ResponseEntity.ok(commentFacade.getByArticleId(articleId));
        return null;
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<DataResponse<CommentDTO>> makeComment(@PathVariable("articleId") String articleId,
                                                                @RequestBody CommentCreateRequest requestBody) {
        //return ResponseEntity.ok(commentFacade.makeComment(articleId, requestBody));
        return null;
    }

    @PostMapping("/{commentId}/answer")
    public ResponseEntity<DataResponse<CommentDTO>> answerToComment(@PathVariable("commentId") String commentId,
                                                                @RequestBody CommentCreateRequest requestBody) {
        //return ResponseEntity.ok(commentFacade.answerToComment(commentId, requestBody));
        return null;
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<DataResponse<String>>  publishCommentById(@PathVariable String id) {
        //return ResponseEntity.ok(commentFacade.publishCommentById(id));
        return null;
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<DataResponse<String>> banCommentById(@PathVariable String id) {
        //return ResponseEntity.ok(commentFacade.banCommentById(id));
        return null;
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CommentDTO>> updateById(@PathVariable String id,
                                                               @RequestBody CommentUpdateRequest requestBody) {
        //return ResponseEntity.ok(commentFacade.updateById(id, requestBody));
        return null;
    }

}
