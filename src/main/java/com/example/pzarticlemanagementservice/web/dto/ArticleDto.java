package com.example.pzarticlemanagementservice.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.net.URL;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDto {
    private UUID author;
    @NotNull @NotBlank @Length(min = 2)
    private String title;
    private List<String> tags;
    @NotNull @NotBlank
    private String content;
    private List<URL> images;
}
