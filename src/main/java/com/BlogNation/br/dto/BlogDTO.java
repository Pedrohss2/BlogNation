package com.BlogNation.br.dto;

import com.BlogNation.br.dto.user.UserMinDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
public class BlogDTO {

    private Long id;

    private String title;

    private String description;

    private Date created_At;

    private Date updated_At;

    private User author_id;

    public BlogDTO(Blog blog) {
        this.id = blog.getId();
        this.title = blog.getTitle();
        this.description = blog.getDescription();
        this.created_At = blog.getCreated_At();
        this.updated_At = blog.getUpdated_At();
        this.author_id = blog.getAuthor_id();
    }
}
