package com.springbootlearning.learningspringboot3.ch4_method_security;

import jakarta.annotation.PostConstruct;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

public class VideoService {

    private final VideoRepository repository;

    public VideoService(VideoRepository repository) {
        this.repository = repository;
    }

    public List<VideoEntity> getVideos() {
        return repository.findAll();
    }

    public VideoEntity create(NewVideo newVideo, String username) {
        return repository.saveAndFlush(new VideoEntity(username, newVideo.name(), newVideo.description()));
    }

    public List<VideoEntity> search(Search search) {
        VideoEntity probe = new VideoEntity();

        if (StringUtils.hasText(search.value())) {
            probe.setName(search.value());
            probe.setDescription(search.value());
        }

        Example<VideoEntity> example = Example.of(probe, ExampleMatcher.matchingAny()
          .withIgnoreCase()
          .withStringMatcher(StringMatcher.CONTAINING));

        return repository.findAll(example);
    }

    public void delete(Long videoId) {
        repository.findById(videoId).map(videoEntity -> {
            repository.delete(videoEntity);
            return true;
        }).orElseThrow(() -> new RuntimeException("No video at " + videoId));
    }

    @PostConstruct
    void initDatabase() {
        repository.save(new VideoEntity("alice", "Need Help with your SPRING BOOOT 3 APP?", 
          "SPRING BOOT 3 will only speed this up and make it super SIMPLE to serve template and raw data."));
        repository.save(new VideoEntity("alice", "Don't do THIS to your own CODE!",
          "As a pro developer, never ever EVER do this to your code. Because you'll ultimately by doing it to YOURSELF"));
        repository.save(new VideoEntity("bob", "SECRETS to fix BROKEN CODE!",
          "Discover ways to not only debug your code, but to regain your confidence and get back in the game as a software developer."));
    }
}