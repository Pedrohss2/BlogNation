package com.BlogNation.br.service;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.dto.CommentDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.model.Comment;
import com.BlogNation.br.repository.CommentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMappers;

    public CommentDTO addComment(CommentDTO commentDTO) {
        Comment comment = modelMappers.map(commentDTO, Comment.class);

        comment.setPublishDate(Date.from(Instant.now()));
        comment.setUpdated_At(Date.from(Instant.now()));

        comment = commentRepository.save(comment);

        return modelMappers.map(comment, CommentDTO.class);
    }

}
