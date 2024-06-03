package com.springbootlearning.learningspringboot3.ch7;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsermanagementRepository extends JpaRepository<UserAccount, Long> {
    
}
