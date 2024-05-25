package com.BlogNation.br.dto;

import com.BlogNation.br.model.Blog;
import com.BlogNation.br.model.Comment;
import com.BlogNation.br.model.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Field 'name' cannot be blank")
    private String content;

    private Date publishDate;

    private UserMinDTO author_id;

    private BlogDTO blog_id;

    private Date updated_At;

    public CommentDTO(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.publishDate = comment.getPublishDate();
        this.author_id = new UserMinDTO(comment.getAuthor());
        this.blog_id = new BlogDTO(comment.getBlog());
        this.updated_At = comment.getUpdated_At();
    }
}
