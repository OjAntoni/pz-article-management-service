package com.example.pzarticlemanagementservice.web.mapper;

import com.example.pzarticlemanagementservice.model.Article;
import com.example.pzarticlemanagementservice.web.dto.ArticleDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    Article toEntity(ArticleDto dto);
}
