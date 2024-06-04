package com.springbootlearning.learningspringboot3.ch8;

import org.springframework.data.repository.Repository;

public interface UserRepository extends Repository<UserAccount, Long> {

    UserAccount findByUsername(String username);
    
}
