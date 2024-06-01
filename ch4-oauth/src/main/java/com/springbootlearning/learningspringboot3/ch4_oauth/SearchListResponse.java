package com.springbootlearning.learningspringboot3.ch4_oauth;

/**
 * SearchListResponse
 */
record SearchListResponse(String kind, String etag, String nextPageTOken, String prevPageToken,
  PageInfo pageInfo, SearchResult[] items) {
}