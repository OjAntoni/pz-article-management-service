package com.example.pzarticlemanagementservice.service;

import com.example.pzarticlemanagementservice.web.dto.CommentResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "user-activity-service", url = "${user-activity-service.url}")
public interface UserActivityService {
    @GetMapping("/api/v1/comment")
    List<CommentResponseDto> getAllComments(@RequestParam UUID postId);
    @DeleteMapping("/api/v1/comment/{id}")
    void deleteComment(@PathVariable UUID id);
}
