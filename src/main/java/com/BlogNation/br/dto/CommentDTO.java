package com.BlogNation.br.dto;

import com.BlogNation.br.model.Comment;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
public class CommentDTO {

    private Long id;

    @NotBlank(message = "Field 'content' cannot be blank")
    private String content;

    private Date publishDate;

    @NotBlank(message = "Field 'author_id' cannot be blank")
    private UserMinDTO author_id;

    @NotBlank(message = "Field 'blog_id' cannot be blank")
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
