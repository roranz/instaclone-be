package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.LikeDto;
import com.instaclone.be.entities.Like;

@Mapper
public interface LikeMapper {

    @Mapping(target = "pictureId", source = "picture.pictureId")
    @Mapping(target = "profileId", source = "profile.profileId")
    LikeDto toDto(Like like);

    @Mapping(target = "picture.pictureId", source = "pictureId")
    @Mapping(target = "profile.profileId", source = "profileId")
    Like fromDto(LikeDto likeDto);

    List<LikeDto> toListDto(List<Like> likes);

    List<Like> fromListDto(List<LikeDto> likesDto);
    
}