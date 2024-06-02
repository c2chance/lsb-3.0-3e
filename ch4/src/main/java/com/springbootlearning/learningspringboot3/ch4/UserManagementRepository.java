package com.springbootlearning.learningspringboot3.ch4;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserManagementRepository extends JpaRepository<UserAccount, Long> {
}
