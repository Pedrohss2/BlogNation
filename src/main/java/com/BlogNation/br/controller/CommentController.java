package com.BlogNation.br.controller;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.dto.CommentDTO;
import com.BlogNation.br.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping
    public ResponseEntity<CommentDTO> addComent(@Valid @RequestBody CommentDTO commentDTO) {

        commentDTO = commentService.addComment(commentDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }
}
