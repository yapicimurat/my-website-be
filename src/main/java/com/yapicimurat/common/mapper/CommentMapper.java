package com.yapicimurat.common.mapper;

import com.yapicimurat.dto.CommentDTO;
import com.yapicimurat.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);
    List<CommentDTO> convertCommentListToCommentDTOList(List<Comment> source);
    CommentDTO convertCommentToCommentDTO(Comment source);
}
