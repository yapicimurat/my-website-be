package com.yapicimurat.service.impl.facade;

import com.yapicimurat.common.mapper.CommentMapper;
import com.yapicimurat.controller.request.CommentCreateRequest;
import com.yapicimurat.controller.request.CommentUpdateRequest;
import com.yapicimurat.controller.response.DataResponse;
import com.yapicimurat.controller.response.SuccessDataResponse;
import com.yapicimurat.dto.CommentDTO;
import com.yapicimurat.service.CommentService;
import com.yapicimurat.util.ConverterUtil;
import com.yapicimurat.util.GeneralUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CommentFacade {
    private final CommentService commentService;
    private final ModelMapper modelMapper;

    public CommentFacade(CommentService commentService, ModelMapper modelMapper) {
        this.commentService = commentService;
        this.modelMapper = modelMapper;
    }

    public DataResponse<CommentDTO> getById(String id) {
        final CommentDTO commentDTO = modelMapper.map(commentService.getById(UUID.fromString(id)), CommentDTO.class);

        return new SuccessDataResponse<>(commentDTO, "");
    }

    public DataResponse<List<CommentDTO>> getByArticleId(String articleId) {
        final List<CommentDTO> commentDTOList = CommentMapper.INSTANCE
                .convertCommentListToCommentDTOList(commentService.getByArticleId(UUID.fromString(articleId)));

        return new SuccessDataResponse<>(commentDTOList, "");
    }

    public DataResponse<CommentDTO> makeComment(String articleId, CommentCreateRequest requestBody) {
        final CommentDTO commentDTO = modelMapper
                .map(commentService.makeComment(UUID.fromString(articleId), requestBody), CommentDTO.class);

        return new SuccessDataResponse<>(commentDTO, "");
    }

    public DataResponse<CommentDTO> answerToComment(String commentId, CommentCreateRequest requestBody) {
        final CommentDTO commentDTO = modelMapper
                .map(commentService.answerToComment(UUID.fromString(commentId), requestBody), CommentDTO.class);

        return new SuccessDataResponse<>(commentDTO, "");
    }

    public DataResponse<String> publishCommentById(String id) {
        final String commentId = commentService.publishCommentById(UUID.fromString(id)).toString();

        return new SuccessDataResponse<>(commentId, "");
    }

    public DataResponse<String> banCommentById(String id) {
        final String commentId = commentService.banCommentById(UUID.fromString(id)).toString();

        return new SuccessDataResponse<>(commentId, "");
    }

    public DataResponse<CommentDTO> updateById(String id, CommentUpdateRequest requestBody) {
        final CommentDTO commentDTO = modelMapper.map(commentService.updateById(UUID.fromString(id), requestBody),
                CommentDTO.class);

        return new SuccessDataResponse<>(commentDTO, "");
    }
}
