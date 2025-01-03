package com.BlogNation.br.dto;

import com.BlogNation.br.model.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CommentMinDTO {

    private Long id;

    private String author;

    private String comment;

    public CommentMinDTO(Comment comment) {
        this.id = comment.getId();
        this.author = comment.getAuthor().getName();
        this.comment = comment.getContent();
    }

}
