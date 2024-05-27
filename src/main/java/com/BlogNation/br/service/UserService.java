package com.BlogNation.br.service;

import com.BlogNation.br.dto.UserDTO;
import com.BlogNation.br.model.User;
import com.BlogNation.br.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ModelMapper modelMappers;


    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserDTO::new);
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("User not found"));

        return new UserDTO(user);
    }

    public UserDTO create(UserDTO userDTO) {
        User user = modelMappers.map(userDTO, User.class);

        user = userRepository.save(user);

        return modelMappers.map(user, UserDTO.class);
    }

    public UserDTO update(Long id, UserDTO userDTO) {
        try {
            User user = modelMappers.map(userDTO, User.class);
            user = userRepository.save(user);
            return modelMappers.map(user, UserDTO.class);
        }
        catch (EntityNotFoundException e) {
            throw new EntityNotFoundException("User not found!");
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (EntityNotFoundException e) {
            throw new DataIntegrityViolationException("Integrity violation");
        }
    }

}
