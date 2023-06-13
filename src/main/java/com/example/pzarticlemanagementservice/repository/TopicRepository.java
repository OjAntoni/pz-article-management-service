package com.example.pzarticlemanagementservice.repository;

import com.example.pzarticlemanagementservice.model.Topic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TopicRepository extends JpaRepository<Topic, UUID> {
    List<Topic> findAllByTitleContainsIgnoreCase(String title);
    List<Topic> findAllByTitleLike(String title);
    List<Topic> findAllByAuthor(UUID uuid);

}
