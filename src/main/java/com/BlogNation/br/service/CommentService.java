package com.BlogNation.br.service;

import com.BlogNation.br.dto.CommentDTO;
import com.BlogNation.br.dto.CommentMinDTO;
import com.BlogNation.br.model.Comment;
import com.BlogNation.br.repository.CommentRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;


@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMappers;


    public Page<CommentMinDTO> findAllById(Long id, Pageable pageable) {
        Page<Comment> comments = commentRepository.findAllById(id, pageable);

        return comments.map(CommentMinDTO::new);
    }

    public CommentDTO create(CommentDTO commentDTO) {
        Comment comment = modelMappers.map(commentDTO, Comment.class);

        comment.setPublishDate(Date.from(Instant.now()));
        comment.setUpdated_At(Date.from(Instant.now()));

        comment = commentRepository.save(comment);

        return modelMappers.map(comment, CommentDTO.class);
    }

    public CommentDTO update(Long id, CommentDTO commentDTO) {
        try {
            Comment comment = modelMappers.map(commentDTO, Comment.class);

            commentRepository.save(comment);

            return modelMappers.map(comment, CommentDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new DataIntegrityViolationException("User not found");
        }
    }

    public void delete(Long id) {
        commentRepository.deleteById(id);
    }
}
