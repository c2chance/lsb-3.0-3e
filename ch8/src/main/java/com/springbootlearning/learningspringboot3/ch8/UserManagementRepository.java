package com.springbootlearning.learningspringboot3.ch8;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
    
    boolean existsByUsername(String username);
    
}
