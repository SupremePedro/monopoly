//package com.pedrofactory.monopoly.config;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//
//
//@Configuration
//@EnableAuthorizationServer
//public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {
//    private AuthenticationManager authenticationManager;
//    private static final String GRANT_TYPE_PASSWORD = "password";
//    private static final String AUTHORIZATION_CODE = "authorization_code";
//    private static final String REFRESH_TOKEN = "refresh_token";
//    private static final String SCOPE_READ = "read";
//    private static final String SCOPE_WRITE = "write";
//    private static final String TRUST = "trust";
//    private static final int VALID_FOREVER = -1;
//    public static final String CLIENT_ID = "my-client";
//    public static final String CLIENT_SECRET = "{noop}my-secret";
//
//    @Override
//    public void configure(ClientDetailsServiceConfigurer client) throws Exception {
//        client .inMemory()
//                .withClient(CLIENT_ID)
//                .secret(CLIENT_SECRET)
//                .authorizedGrantTypes(GRANT_TYPE_PASSWORD, AUTHORIZATION_CODE, REFRESH_TOKEN)
//                .scopes(SCOPE_READ, SCOPE_WRITE, TRUST)
//                .accessTokenValiditySeconds(VALID_FOREVER)
//                .refreshTokenValiditySeconds(VALID_FOREVER);
//    }
//
//    @Bean
//    TokenStore tokenStore(){
//        return new InMemoryTokenStore();
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints){
//        endpoints.tokenStore(tokenStore()).authenticationManager(authenticationManager);
//    }
//}
