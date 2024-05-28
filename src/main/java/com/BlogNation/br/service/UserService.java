package com.BlogNation.br.service;

import com.BlogNation.br.dto.UserDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.model.User;
import com.BlogNation.br.repository.BlogRepository;
import com.BlogNation.br.repository.UserFollowRepository;
import com.BlogNation.br.repository.UserRepository;
import com.BlogNation.br.service.exception.DatabaseException;
import com.BlogNation.br.service.exception.ResourceNotFoundException;
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
    private BlogRepository blogRepository;

    @Autowired
    private UserFollowRepository userFollowRepository;

    @Autowired
    private ModelMapper modelMappers;


    public Page<UserDTO> getAllUsers(Pageable pageable) {
        Page<User> users = userRepository.findAll(pageable);

        return users.map(UserDTO::new);
    }

    public UserDTO findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));

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
            throw new ResourceNotFoundException("User not found!");
        }
    }

    public void delete(Long id) {
        try {
            userRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Integrity violation");
        }
    }

    public void follow(Long id, Long blogId) {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        user.getBlogs().add(blog);
        blog.getFollowers().add(user);

        userRepository.save(user);
        blogRepository.save(blog);
    }

    public void unfollow(Long id, Long blogId)  {

        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User not found"));
        Blog blog = blogRepository.findById(blogId).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        user.getBlogs().remove(blog);
        blog.getFollowers().remove(user);

        userRepository.save(user);
        blogRepository.save(blog);
    }

}
