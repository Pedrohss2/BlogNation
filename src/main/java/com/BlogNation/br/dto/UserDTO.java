package com.BlogNation.br.dto;

import com.BlogNation.br.model.User;
import com.BlogNation.br.model.enums.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotBlank(message = "Field 'name' cannot be blank")
    private String name;
    @Email(message = "Field 'email' need be a true email")
    private String email;
    @Size(min = 4, message = "Password must be longer than 4 characters ")
    private String password;
    private  UserRole role;
}
