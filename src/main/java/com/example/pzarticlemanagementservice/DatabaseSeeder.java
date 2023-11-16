package com.example.pzarticlemanagementservice;

import com.example.pzarticlemanagementservice.model.Article;
import com.example.pzarticlemanagementservice.model.Topic;
import com.example.pzarticlemanagementservice.repository.ArticleRepository;
import com.example.pzarticlemanagementservice.repository.TopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.stream.IntStream;

@Component
public class DatabaseSeeder implements CommandLineRunner {

    private final ArticleRepository articleRepository;
    @Autowired
    private TopicRepository topicRepository;

    public DatabaseSeeder(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public void run(String... args) {
        Random r = new Random();
        IntStream.range(0, 100).forEach(i -> {
            try {
                Topic topic = new Topic();
                topic.setDescription("Description"+i);
                topic.setTitle("Title"+i);
                topic.setAuthor(new Random().nextLong());
                Topic save = topicRepository.save(topic);

                Article article = new Article();
                article.setId(UUID.randomUUID());
                article.setAuthor(UUID.randomUUID());
                article.setTitle("Test Title " + i);
                article.setTags(Arrays.asList("Tag"+r.nextInt(10), "Tag"+r.nextInt(10)));
                article.setCreatedAt(LocalDateTime.now());
                article.setContent("Test content " + i);
                article.setImages(List.of(new URL("http://example.com/image" + i + ".jpg")));
                article.setTopic(save);
//                articleRepository.save(article);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }
}
