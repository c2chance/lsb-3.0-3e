package com.springbootlearning.learningspringboot3.ch4_oauth;

/**
 * SearchResult
 */
public record SearchResult(String kind, String etag, SearchId id,
  SearchSnippet snippet) {
}