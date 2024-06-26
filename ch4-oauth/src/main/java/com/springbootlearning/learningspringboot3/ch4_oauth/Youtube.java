package com.springbootlearning.learningspringboot3.ch4_oauth;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

/**
 * Youtube
 */
interface YouTube {

    @GetExchange("/search?part=snippet&type=video")
    SearchListResponse channelVideos(
        @RequestParam String channelId,
        @RequestParam int maxResults,
        @RequestParam Sort order);

    enum Sort {
        DATE("date"),
        VIEW_COUNT("viewCount"),
        TITLE("tile"),
        RATING("rating");

        private final String type;

        Sort(String type) {
            this.type=type;
        }
    }
}