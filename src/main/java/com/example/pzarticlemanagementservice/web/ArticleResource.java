package com.example.pzarticlemanagementservice.web;

import com.example.pzarticlemanagementservice.model.Article;
import com.example.pzarticlemanagementservice.model.Topic;
import com.example.pzarticlemanagementservice.repository.ArticleSpecification;
import com.example.pzarticlemanagementservice.service.ArticleService;
import com.example.pzarticlemanagementservice.web.dto.ArticleDto;
import com.example.pzarticlemanagementservice.web.mapper.ArticleMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/article")
@AllArgsConstructor
public class ArticleResource {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping("/search")
    public ResponseEntity<?> searchArticles(
            @RequestParam(required = false) UUID author,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) List<String> tags,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end,
            @RequestParam(required = false, defaultValue = "0") int page,
            @RequestParam(required = false, defaultValue = "10") int size
    ) {
        Specification<Article> spec = Specification.where(
                ArticleSpecification.hasAuthor(author)
                        .and(ArticleSpecification.hasTitle(title))
                        .and(ArticleSpecification.hasTags(tags))
                        .and(ArticleSpecification.createdAtBetween(start, end))
        );
        return new ResponseEntity<>(articleService.getAll(spec, page, Math.max(10, size)), HttpStatus.OK);
    }

    @GetMapping("/all/{id}")
    ResponseEntity<List<Article>> getByAllForUser(@PathVariable UUID id){
        return new ResponseEntity<>(articleService.getAllForUser(id), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> updateArticle(@PathVariable UUID id, @RequestBody Article newArticle){
        Article update = articleService.update(id, newArticle);
        return update != null ? new ResponseEntity<>(update, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable UUID id){
        articleService.delete(id);
        return new ResponseEntity<>(HttpStatus.GONE);
    }

    @PostMapping
    public ResponseEntity<?> saveArticle(@RequestBody @Valid ArticleDto dto, BindingResult result){
        if (result.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            result.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(articleService.save(articleMapper.toEntity(dto)), HttpStatus.CREATED);
    }

}
