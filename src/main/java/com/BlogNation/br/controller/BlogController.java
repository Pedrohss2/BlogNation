package com.BlogNation.br.controller;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.dto.UserDTO;
import com.BlogNation.br.service.BlogService;
import com.BlogNation.br.service.UserService;
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
@RequestMapping(value = "/blogs")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @PostMapping
    public ResponseEntity<BlogDTO> create(@Valid @RequestBody BlogDTO blogDTO) {

        blogDTO = blogService.create(blogDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(blogDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

}
