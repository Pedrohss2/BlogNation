package com.BlogNation.br.service;

import com.BlogNation.br.model.User;
import com.BlogNation.br.service.exception.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthorizationService authorizationService;

    public void validateSelf(Long userId) {
        User user = authorizationService.authenticated();
        if(!user.hasRole("ADMIN") && !user.getId().equals(userId)) {
            throw new ForbiddenException("Access danied");
        }
    }
}
