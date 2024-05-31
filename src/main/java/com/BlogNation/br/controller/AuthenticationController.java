package com.BlogNation.br.controller;

import com.BlogNation.br.dto.user.AuthenticationDTO;
import com.BlogNation.br.dto.user.LoginDTO;
import com.BlogNation.br.dto.user.RegisterDTO;
import com.BlogNation.br.dto.user.UserDTO;
import com.BlogNation.br.model.User;
import com.BlogNation.br.repository.UserRepository;
import com.BlogNation.br.service.AuthorizationService;
import com.BlogNation.br.service.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;

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

    @GetMapping("/users/me")
    public ResponseEntity<UserDTO> getMe() {
        UserDTO userDTO = authorizationService.getMe();
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

}
