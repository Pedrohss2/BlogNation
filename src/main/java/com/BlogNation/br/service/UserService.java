package com.BlogNation.br.service;

import com.BlogNation.br.config.ModelMapperConfig;
import com.BlogNation.br.dto.UserDTO;
import com.BlogNation.br.model.User;
import com.BlogNation.br.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMappers;

    public UserDTO create(UserDTO userDTO) {
        User user = modelMappers.map(userDTO, User.class);

        user = userRepository.save(user);

        return modelMappers.map(user, UserDTO.class);
    }


}
