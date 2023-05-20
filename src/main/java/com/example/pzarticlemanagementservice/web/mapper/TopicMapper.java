package com.example.pzarticlemanagementservice.web.mapper;

import com.example.pzarticlemanagementservice.model.Topic;
import com.example.pzarticlemanagementservice.web.dto.TopicDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TopicMapper {
    Topic toEntity(TopicDto dto);
}
