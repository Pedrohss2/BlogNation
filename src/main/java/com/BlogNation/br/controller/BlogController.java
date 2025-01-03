package com.BlogNation.br.controller;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.service.BlogService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @GetMapping
    @Operation(summary = "Get blog by id")
    public ResponseEntity<BlogDTO> findById(@RequestParam(name = "id", defaultValue = "0") Long id) {
        BlogDTO blogDTO = blogService.findById(id);

        return ResponseEntity.ok(blogDTO);
    }

    @PostMapping
    @Operation(summary = "Create a blog")
    public ResponseEntity<BlogDTO> create(@Valid @RequestBody BlogDTO blogDTO) {

        blogDTO = blogService.create(blogDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(blogDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a blog by id")
    public ResponseEntity<BlogDTO> update(@PathVariable Long id, @Valid @RequestBody BlogDTO blogDTO) {
        blogDTO = blogService.update(id, blogDTO);

        return ResponseEntity.ok(blogDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a blog by id")
    public ResponseEntity<BlogDTO> delete(Long id) {
        blogService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
