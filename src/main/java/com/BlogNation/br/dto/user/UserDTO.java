package com.BlogNation.br.dto.user;

import com.BlogNation.br.model.User;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserDTO {

    private Long id;
    @NotEmpty(message = "Field 'name' cannot be blank")
    private String name;
    private String email;
    @Size(min = 4, message = "Password must be longer than 4 characters ")
    private String password;

    public UserDTO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.password = user.getPassword();
    }
}
