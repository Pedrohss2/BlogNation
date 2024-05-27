package com.BlogNation.br.controller;

import com.BlogNation.br.dto.UserDTO;
import com.BlogNation.br.service.UserService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userService.getAllUsers(pageable);

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @Transactional
    @PostMapping
    public ResponseEntity<UserDTO> create(@Valid @RequestBody UserDTO userDTO) {

        userDTO = userService.create(userDTO);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(userDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/up")
    public ResponseEntity<UserDTO> update(@Valid Long id, @RequestBody UserDTO userDTO) {
        userDTO = userService.update(id, userDTO);

        return ResponseEntity.ok(userDTO);
    }

    @DeleteMapping
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/user/{user_id}/follow/{blog_id}")
    public ResponseEntity<Void> follow(@PathVariable Long user_id, @PathVariable Long blog_id) {
        userService.userFollow(user_id, blog_id);
        return ResponseEntity.noContent().build();
    }
}
