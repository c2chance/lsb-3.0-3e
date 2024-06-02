package com.springbootlearning.learningspringboot3.ch4;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserAccount, Long> {
    UserAccount findByUsername(String username);
}
