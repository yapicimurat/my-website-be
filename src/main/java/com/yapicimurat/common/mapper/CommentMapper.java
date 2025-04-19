package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.comment.CommentDTO;
import com.yapicimurat.dto.comment.CommentInputDTO;
import com.yapicimurat.dto.comment.ParentCommentDTO;
import com.yapicimurat.model.Comment;
import com.yapicimurat.model.projection.CommentSummaryDTO;
import com.yapicimurat.web.input.comment.CommentInput;
import com.yapicimurat.web.output.comment.CommentOutput;
import com.yapicimurat.web.output.comment.CommentSummaryOutput;
import com.yapicimurat.web.output.comment.ParentCommentOutput;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    CommentInputDTO convertCommentInputToCommentInputDTO(CommentInput source);
    CommentOutput convertCommentDTOToCommentOutput(CommentDTO source);
    Comment convertCommentInputDTOToCommentEntity(CommentInputDTO source);
    @Mapping(target = "isAnswer", source = "answer")
    @Mapping(target = "amountOfAnswers", expression = "java(source.getNoProxyAnswers().size())")
    CommentDTO convertCommentEntityToCommentDTO(Comment source);
    ParentCommentDTO convertCommentToParentCommentDTO(Comment source);
    ParentCommentOutput convertCommentDTOToParentCommentOutput(ParentCommentDTO source);
    List<CommentDTO> convertCommentEntityListToCommentDTOList(List<Comment> source);
    List<CommentOutput> convertCommentDTOListToCommentOutputList(List<CommentDTO> source);
    List<CommentSummaryDTO> toCommentSummaryDTOList(List<Comment> source);
    List<CommentSummaryOutput> toCommentSummaryOutputList(List<CommentSummaryDTO> source);
    CommentSummaryDTO toCommentSummaryDTO(Comment source);

    @Mapping(target = "amountOfAnswers", expression = "java(amountOfAnswers)")
    CommentSummaryDTO toCommentSummaryDTO(Comment source, int amountOfAnswers);
}
