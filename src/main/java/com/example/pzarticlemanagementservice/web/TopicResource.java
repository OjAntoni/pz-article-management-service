package com.example.pzarticlemanagementservice.web;

import com.example.pzarticlemanagementservice.model.Topic;
import com.example.pzarticlemanagementservice.service.TopicService;
import com.example.pzarticlemanagementservice.web.dto.TopicDto;
import com.example.pzarticlemanagementservice.web.mapper.TopicMapper;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/topic")
public class TopicResource {
    private TopicMapper topicMapper;
    private TopicService topicService;

    @PostMapping
    ResponseEntity<?> createNewTopic(@RequestBody @Valid TopicDto dto, BindingResult result){
        if (result.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            result.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        Topic topic = topicMapper.toEntity(dto);
        return new ResponseEntity<>(topicService.save(topic), HttpStatus.CREATED);
    }

    @GetMapping("/{uuid}")
    ResponseEntity<Topic> getById(@PathVariable UUID uuid){
        return new ResponseEntity<>(topicService.get(uuid), HttpStatus.OK);
    }

    @GetMapping("/all")
    ResponseEntity<List<Topic>> getByAll(){
        return new ResponseEntity<>(topicService.getAll(), HttpStatus.OK);
    }
    @GetMapping("/all/{id}")
    ResponseEntity<List<Topic>> getByAllForUser(@PathVariable UUID id){
        return new ResponseEntity<>(topicService.getAllForUser(id), HttpStatus.OK);
    }

    @GetMapping("/all_by_title")
    ResponseEntity<List<Topic>> getAll(@RequestParam String title){
        System.out.println();
        return new ResponseEntity<>(topicService.getAll(title), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    ResponseEntity<?> updateTopic(@PathVariable UUID id, @RequestBody @Valid TopicDto dto, BindingResult result){
        if (result.hasErrors()) {
            HashMap<String, String> map = new HashMap<>();
            result.getFieldErrors().forEach(e -> map.put(e.getField(), e.getDefaultMessage()));
            return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(topicService.update(id,topicMapper.toEntity(dto)), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Topic> deleteById(@PathVariable UUID id){
        topicService.delete(id);
        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }

}
