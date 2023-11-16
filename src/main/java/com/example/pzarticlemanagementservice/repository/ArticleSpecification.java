package com.example.pzarticlemanagementservice.repository;

import com.example.pzarticlemanagementservice.model.Article;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Collection;

public class ArticleSpecification {
    public static Specification<Article> hasAuthor(long author) {
        return (article, cq, cb) -> author == null ? cb.conjunction() : cb.equal(article.get("author"), author);
    }

    public static Specification<Article> hasTitle(String title) {
        return (article, cq, cb) -> title == null ? cb.conjunction() : cb.like(article.get("title"), "%" + title + "%");
    }

    public static Specification<Article> hasTags(Collection<String> tags) {
        return (root, cq, cb) -> {
            if (tags == null || tags.isEmpty()) {
                return cb.conjunction();
            } else {
                Join<Article, String> tagsJoin = root.join("tags", JoinType.INNER);
                return tagsJoin.in(tags);
            }
        };
    }

    public static Specification<Article> createdAtBetween(LocalDateTime start, LocalDateTime end) {
        return (article, cq, cb) -> {
            if (start == null && end == null) {
                return cb.conjunction();
            } else if (start != null && end != null) {
                return cb.between(article.get("createdAt"), start, end);
            } else if (start != null) {
                return cb.greaterThanOrEqualTo(article.get("createdAt"), start);
            } else {
                return cb.lessThanOrEqualTo(article.get("createdAt"), end);
            }
        };
    }
}
