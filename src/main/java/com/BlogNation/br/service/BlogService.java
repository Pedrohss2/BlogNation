package com.BlogNation.br.service;

import com.BlogNation.br.dto.BlogDTO;
import com.BlogNation.br.model.Blog;
import com.BlogNation.br.repository.BlogRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Date;

@Service
public class BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private ModelMapper modelMappers;

    public BlogDTO create (BlogDTO blogDTO) {
        Blog blog = modelMappers.map(blogDTO, Blog.class);

        blog.setCreated_At(Date.from(Instant.now()));
        blog.setUpdated_At(Date.from(Instant.now()));

        blog = blogRepository.save(blog);

        return modelMappers.map(blog, BlogDTO.class);
    }


}
