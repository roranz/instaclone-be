package com.instaclone.be.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.instaclone.be.dto.MessageDto;
import com.instaclone.be.entities.Message;

@Mapper
public interface MessageMapper {

    @Mapping(target = "senderId", source = "sender.profileId")
    @Mapping(target = "receiverId", source = "receiver.profileId")
    MessageDto toDto(Message message);

    @Mapping(target = "sender.profileId", source = "senderId")
    @Mapping(target = "receiver.profileId", source = "receiverId")
    Message fromDto(MessageDto messageDto);

    List<MessageDto> toListDto(List<Message> messages);

    List<Message> fromListDto(List<MessageDto> messagesDto);
    
}