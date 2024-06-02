package com.springbootlearning.learningspringboot3.ch4;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

interface VideoRepository extends JpaRepository<VideoEntity, Long> {

    List<VideoEntity> findByNameContainsIgnoreCase(String partialName);

    List<VideoEntity> findByDescriptionContainsIgnoreCase(String partialName);

    List<VideoEntity> findByNameContainsOrDescriptionContainsAllIgnoreCase(String partialName);
    
}
