package com.BlogNation.br.controller;

import com.BlogNation.br.dto.user.UserDTO;
import com.BlogNation.br.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/all")
    @Operation(summary = "Get all users")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userService.getAllUsers(pageable);

        return ResponseEntity.ok(users);
    }

    @DeleteMapping("/del/{id}")
    @Operation(summary = "Delete a user by id")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{user_id}/follow/{blog_id}")
    @Operation(summary = "User follow a blog")
    public ResponseEntity<Void> follow(@PathVariable Long user_id, @PathVariable Long blog_id) {
        userService.follow(user_id, blog_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{user_id}/unfollow/{blog_id}")
    @Operation(summary = "User unfollow a blog")
    public ResponseEntity<Void> unFollow(@PathVariable Long user_id, @PathVariable Long blog_id) {
        userService.unfollow(user_id, blog_id);
        return ResponseEntity.noContent().build();
    }

}
