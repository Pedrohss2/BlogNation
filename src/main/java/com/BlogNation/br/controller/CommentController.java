package com.BlogNation.br.controller;

import com.BlogNation.br.dto.CommentDTO;
import com.BlogNation.br.dto.CommentMinDTO;
import com.BlogNation.br.service.CommentService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @GetMapping
    public ResponseEntity<Page<CommentMinDTO>> findAllById(@RequestParam(name = "id", defaultValue = " ") Long id, Pageable pageable) {
        Page<CommentMinDTO> comments = commentService.findAllById(id, pageable);

        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO commentDTO) {

        commentDTO = commentService.create(commentDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CommentDTO> update(Long id, @Valid @RequestBody CommentDTO commentDTO) {
        commentDTO = commentService.update(id, commentDTO);

        return ResponseEntity.ok(commentDTO);
    }

    @DeleteMapping
    public ResponseEntity<CommentDTO> delete(Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
