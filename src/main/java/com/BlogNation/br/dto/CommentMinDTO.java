package com.BlogNation.br.dto;

import com.BlogNation.br.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentMinDTO {

    private Long id;
    private String comment;
    private String author;

    public CommentMinDTO(Comment comment) {
        this.id = comment.getId();
        this.comment = comment.getContent();
        this.author = comment.getAuthor().getName();
    }

}
