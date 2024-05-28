package com.BlogNation.br.dto.user;


import com.BlogNation.br.model.enums.UserRole;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDTO {
    private Long id;
    @NotEmpty(message = "Field 'name' cannot be blank")
    private String name;
    private String email;
    @Size(min = 4, message = "Password must be longer than 4 characters ")
    private String password;
    private UserRole role;
}
