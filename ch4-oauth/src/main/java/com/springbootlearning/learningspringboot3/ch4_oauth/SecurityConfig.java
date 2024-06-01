package com.springbootlearning.learningspringboot3.ch4_oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {

    @Bean
    public OAuth2AuthorizedClientManager clientManager(
      ClientResistrationRepository clientRepo,
      OAuth2AuthoriziedClientRepository authClientRepo) {
        
        OAuth2AuthorizedClientProvider clientProvider = 
          OAuth2AuthorizedClientProviderBuiler.builder() //
            .authorizationCode()
            .refreshToken()
            .clientCredentials()
            .password()
            .build();
        
        DefaultOAuth2AuthorizedClientManager clientManager = 
          new DefaultOAuth2AuthorizedClientManager(
            clientRegRepo, auclientRepo);
        clientManager.setAuthorizedClientProvider(clientProvider);

        return clientManager;
        


    }

    
}