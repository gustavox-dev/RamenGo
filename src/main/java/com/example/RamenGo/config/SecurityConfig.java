package com.example.RamenGo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
//                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
//                .anyRequest().authenticated()
                .anyRequest().permitAll()
                .and()
                .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }
}

//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(authorizeRequests ->
//                        authorizeRequests
//                                .requestMatchers("/api/broths/**").authenticated()
//                                .requestMatchers("/api/proteins/**").authenticated()
//                                .requestMatchers("/api/order/**").authenticated()
//                                .anyRequest().permitAll()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable);
//
//        return http.build();
//    }
//
////    @Bean
////    public UserDetailsService userDetailsService() {
////        UserDetails user = User.withDefaultPasswordEncoder()
////                .username("user")
////                .password("ZtVdh8XQ2U8pWI2gmZ7f796Vh8GllXoN7mr0djNf")
////                .roles("USER")
////                .build();
////        return new InMemoryUserDetailsManager(user);
////    }
//}