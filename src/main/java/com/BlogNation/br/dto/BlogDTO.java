package com.BlogNation.br.dto;

import com.BlogNation.br.model.Blog;
import com.BlogNation.br.model.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BlogDTO {

    private Long id;

    private String title;

    private String description;

    private String photoBaseUrl;

    private Date created_At;

    private Date updated_At;

    private User author_id;

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.photoBaseUrl = blog.getPhotoBaseUrl();
        this.created_At = blog.getCreated_At();
        this.updated_At = blog.getUpdated_At();
        this.author_id = blog.getAuthor_id();
    }
}
