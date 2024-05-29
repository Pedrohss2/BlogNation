package com.BlogNation.br.controller;

import com.BlogNation.br.dto.user.AuthenticationDTO;
import com.BlogNation.br.dto.user.LoginDTO;
import com.BlogNation.br.dto.user.RegisterDTO;
import com.BlogNation.br.dto.user.UserDTO;
import com.BlogNation.br.model.User;
import com.BlogNation.br.repository.UserRepository;
import com.BlogNation.br.service.AuthorizationService;
import com.BlogNation.br.service.TokenService;
import com.BlogNation.br.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private AuthorizationService authorizationService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable Long id) {
        UserDTO userDTO = userService.findById(id);
        return ResponseEntity.ok(userDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginDTO> login(@RequestBody AuthenticationDTO dto) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((User) auth.getPrincipal());

        return ResponseEntity.ok(new LoginDTO(token));
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterDTO> register(@Valid @RequestBody RegisterDTO dto) {

        if(repository.findByEmail(dto.getEmail()) != null){
            return ResponseEntity.badRequest().build();
        }

        String encryptedPassword = new BCryptPasswordEncoder().encode(dto.getPassword());
        User user = new User(dto.getName(), dto.getEmail(), encryptedPassword, dto.getRole());
        this.repository.save(user);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<Page<UserDTO>> getAllUsers(Pageable pageable) {
        Page<UserDTO> users = userService.getAllUsers(pageable);

        return ResponseEntity.ok(users);
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
        userService.follow(user_id, blog_id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{user_id}/unfollow/{blog_id}")
    public ResponseEntity<Void> unFollow(@PathVariable Long user_id, @PathVariable Long blog_id) {
        userService.unfollow(user_id, blog_id);
        return ResponseEntity.noContent().build();
    }

}
