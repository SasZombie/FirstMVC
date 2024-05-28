package com.wad.firstmvc.repository;

import com.wad.firstmvc.domain.Product;
import com.wad.firstmvc.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
