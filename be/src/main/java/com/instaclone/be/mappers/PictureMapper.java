package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.PictureDto;
import com.instaclone.be.entities.Picture;

@Mapper
public interface PictureMapper {

    @Mapping(target = "profileId", source = "profile.profileId")
    @Mapping(target = "pictureTypeId", source = "pictureType.pictureTypeId")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "likes", source = "likes")
    PictureDto toDto(Picture picture);

    @Mapping(target = "profile.profileId", source = "profileId")
    @Mapping(target = "pictureType.pictureTypeId", source = "pictureTypeId")
    @Mapping(target = "comments", source = "comments")
    @Mapping(target = "likes", source = "likes")
    Picture fromDto(PictureDto pictureDto);

    List<PictureDto> toListDto(List<Picture> pictures);

    List<Picture> fromListDto(List<PictureDto> picturesDto);
    
}
