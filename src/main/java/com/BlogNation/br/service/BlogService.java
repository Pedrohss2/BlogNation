package com.BlogNation.br.service;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.repository.BlogRepository;
import com.BlogNation.br.service.exception.DatabaseException;
import com.BlogNation.br.service.exception.ResourceNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMappers;

    public BlogDTO findById(Long id) {
        Blog blog = blogRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Blog not found"));

        return new BlogDTO(blog);
    }

    public BlogDTO create(BlogDTO blogDTO) {
        Blog blog = modelMappers.map(blogDTO, Blog.class);

        blog.setUpdated_At(Date.from(Instant.now()));

        blog = blogRepository.save(blog);

        return modelMappers.map(blog, BlogDTO.class);
    }

    public BlogDTO update(Long id, BlogDTO blogDTO) {
        try {
            Blog blog = modelMappers.map(blogDTO, Blog.class);

            blogRepository.save(blog);

            return modelMappers.map(blog, BlogDTO.class);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("User not found");
        }
    }

    public void delete(Long id) {
        try {
            blogRepository.deleteById(id);
        }
        catch (DataIntegrityViolationException e) {
            throw new DatabaseException("User not found");
        }
    }

}
