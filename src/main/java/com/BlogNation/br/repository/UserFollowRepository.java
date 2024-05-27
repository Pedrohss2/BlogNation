package com.BlogNation.br.repository;

import com.BlogNation.br.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserFollowRepository extends JpaRepository<User, Long> {

}
