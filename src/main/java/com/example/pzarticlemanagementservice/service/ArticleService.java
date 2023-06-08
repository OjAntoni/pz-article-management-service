package com.example.pzarticlemanagementservice.service;

import com.example.pzarticlemanagementservice.model.Article;
import com.example.pzarticlemanagementservice.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public Article save(Article article){
        article.setCreatedAt(LocalDateTime.now());
        return articleRepository.save(article);
    }

    public Article update (UUID id, Article newArticle){
        return articleRepository.findById(id)
                .map(article -> {
                    article.setAuthor(newArticle.getAuthor());
                    article.setTitle(newArticle.getTitle());
                    article.setTags(newArticle.getTags());
                    article.setCreatedAt(newArticle.getCreatedAt());
                    article.setContent(newArticle.getContent());
                    article.setImages(newArticle.getImages());
                    return articleRepository.save(article);
                })
                .orElse(null);
    }

    public void delete (UUID uuid){
        articleRepository.deleteById(uuid);
    }

    public List<Article> getAll(Specification<Article> spec, int page, int size){
        return articleRepository.findAll(spec, PageRequest.of(page, size)).getContent();
    }

    public List<Article> getAllForUser(UUID id){
        return articleRepository.findAllByAuthor(id);
    }

}
