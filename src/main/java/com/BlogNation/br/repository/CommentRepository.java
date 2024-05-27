package com.BlogNation.br.repository;

import com.BlogNation.br.model.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(nativeQuery = true, value = "SELECT * FROM Comment AS obj WHERE obj.id = id")
    Page<Comment> findAllById(Long id, Pageable pageable);

}
