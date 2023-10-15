package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.instaclone.be.dto.PictureTypeDto;
import com.instaclone.be.entities.PictureType;

@Mapper
public interface PictureTypeMapper {

    PictureTypeDto toDto(PictureType pictureType);

    PictureType fromDto(PictureTypeDto pictureTypeDto);

    List<PictureTypeDto> toListDto(List<PictureType> pictureTypes);

    List<PictureType> fromListDto(List<PictureTypeDto> pictureTypesDto);

}