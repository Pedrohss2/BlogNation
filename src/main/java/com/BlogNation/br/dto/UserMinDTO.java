package com.BlogNation.br.dto;

import com.BlogNation.br.model.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserMinDTO {

    private Long id;

    public UserMinDTO(User user) {
        this.id = user.getId();
    }
}
