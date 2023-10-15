package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.CommentDto;
import com.instaclone.be.entities.Comment;

@Mapper
public interface CommentMapper {

    @Mapping(target = "pictureId", source = "picture.pictureId")
    @Mapping(target = "commenterId", source = "commenter.profileId")
    @Mapping(target = "parentCommentId", source = "parentComment.commentId")
    CommentDto toDto(Comment comment);

    @Mapping(target = "picture.pictureId", source = "pictureId")
    @Mapping(target = "commenter.profileId", source = "commenterId")
    @Mapping(target = "parentComment.commentId", source = "parentCommentId")
    Comment fromDto(CommentDto commentDto);

    List<CommentDto> toListDto(List<Comment> comments);
    
    List<Comment> fromListDto(List<CommentDto> commentsDto);
}
