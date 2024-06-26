package com.BlogNation.br.controller;

import com.BlogNation.br.dto.CommentDTO;
import com.BlogNation.br.dto.CommentMinDTO;
import com.BlogNation.br.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
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
    @Operation(summary = "Get all comment that one blog by id")
    public ResponseEntity<Page<CommentMinDTO>> findAllById(@RequestParam(name = "id", defaultValue = " ") Long id, Pageable pageable) {
        Page<CommentMinDTO> comments = commentService.findAllById(id, pageable);

        return ResponseEntity.ok(comments);
    }

    @PostMapping
    @Operation(summary = "Create a comment")
    public ResponseEntity<CommentDTO> create(@Valid @RequestBody CommentDTO commentDTO) {

        commentDTO = commentService.create(commentDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(commentDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a comment")
    public ResponseEntity<CommentDTO> update(@PathVariable Long id, @Valid @RequestBody CommentDTO commentDTO) {
        commentDTO = commentService.update(id, commentDTO);

        return ResponseEntity.ok(commentDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a comment")
    public ResponseEntity<CommentDTO> delete(@PathVariable Long id) {
        commentService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
