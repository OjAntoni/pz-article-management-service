package com.example.pzarticlemanagementservice.service;

import com.example.pzarticlemanagementservice.model.Topic;
import com.example.pzarticlemanagementservice.repository.TopicRepository;
import com.example.pzarticlemanagementservice.web.dto.TopicDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TopicService {
    private TopicRepository topicRepository;

    public Topic save(Topic topic) {
        return topicRepository.save(topic);
    }

    public Topic update(UUID id, Topic topic) {
        return topicRepository.findById(id)
                .map(t -> {
                            t.setAuthor(topic.getAuthor());
                            t.setTitle(topic.getTitle());
                            t.setDescription(topic.getDescription());
                            return topicRepository.save(t);
                        }
                ).orElse(null);
    }

    public void delete(UUID uuid){
        topicRepository.deleteById(uuid);
    }

    public Topic get(UUID uuid){
        return topicRepository.findById(uuid).orElse(null);
    }

    public List<Topic> getAll(){
        return topicRepository.findAll();
    }
    public List<Topic> getAll(String title){
        return topicRepository.findAllByTitleContainsIgnoreCase(title);
    }
}
