package com.example.pzarticlemanagementservice.repository;

import com.example.pzarticlemanagementservice.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.UUID;

public interface ArticleRepository extends JpaRepository<Article, UUID>, JpaSpecificationExecutor<Article> {
    List<Article> findAllByAuthor(UUID id);
}
