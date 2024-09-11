package com.yapicimurat.web.controller;

import com.yapicimurat.common.mapper.CommentMapper;
import com.yapicimurat.dto.comment.CommentInputDTO;
import com.yapicimurat.service.CommentService;
import com.yapicimurat.web.controller.response.SuccessDataResponse;
import com.yapicimurat.web.controller.response.DataResponse;
import com.yapicimurat.web.input.comment.CommentInput;
import com.yapicimurat.web.output.comment.CommentOutput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:3000", methods = {RequestMethod.GET, RequestMethod.POST}, maxAge = 3600)
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<DataResponse<CommentOutput>> getById(@PathVariable("id") String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOToCommentOutput(commentService.getById(UUID.fromString(id))),
                        ""
                )
        );
    }

    @GetMapping("/{id}/answers")
    public ResponseEntity<DataResponse<List<CommentOutput>>> getCommentAnswers(@PathVariable("id") String parentCommentId) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOListToCommentOutputList(commentService.getCommentAnswers(UUID.fromString(parentCommentId))),
                        ""
                )
        );
    }

    @GetMapping("/article/{articleId}/comment")
    public ResponseEntity<DataResponse<List<CommentOutput>>> getByArticleId(@PathVariable("articleId") String articleId) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOListToCommentOutputList(commentService.getAllByArticle(UUID.fromString(articleId))),
                        ""
                )
        );
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<DataResponse<CommentOutput>> makeComment(@PathVariable("articleId") String articleId,
                                                                @RequestBody CommentInput commentInput) {
        CommentInputDTO commentInputDTO = CommentMapper.INSTANCE.convertCommentInputToCommentInputDTO(commentInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOToCommentOutput(commentService.makeComment(
                                        UUID.fromString(articleId),
                                        commentInputDTO
                                )),
                        ""
                )
        );
    }

    @PostMapping("/{commentId}/answer")
    public ResponseEntity<DataResponse<String>> answerToComment(@PathVariable("commentId") String commentId,
                                                                @RequestBody CommentInput commentInput) {
        CommentInputDTO commentInputDTO = CommentMapper.INSTANCE.convertCommentInputToCommentInputDTO(commentInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                                commentService.answerToComment(
                                        UUID.fromString(commentId),
                                        commentInputDTO
                                ),
                        ""
                )
        );
    }

    @PutMapping("/{id}/publish")
    public ResponseEntity<DataResponse<CommentOutput>> publishCommentById(@PathVariable String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOToCommentOutput(commentService.publishCommentById(UUID.fromString(id))),
                        ""
                )
        );
    }

    @PutMapping("/{id}/ban")
    public ResponseEntity<DataResponse<CommentOutput>> banCommentById(@PathVariable String id) {
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOToCommentOutput(commentService.banCommentById(UUID.fromString(id))),
                        ""
                )
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<DataResponse<CommentOutput>> updateById(@PathVariable String id,
                                                               @RequestBody CommentInput commentInput) {
        CommentInputDTO commentInputDTO = CommentMapper.INSTANCE.convertCommentInputToCommentInputDTO(commentInput);
        return ResponseEntity.ok(
                SuccessDataResponse.createSuccessDataResponse(
                        CommentMapper.INSTANCE
                                .convertCommentDTOToCommentOutput(commentService.updateById(
                                        UUID.fromString(id),
                                        commentInputDTO)
                            ),
                        ""
                )
        );
    }

}
