package com.mediaHub.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration

//ye CORS related configuration ke liye hai. like frontend se backend ko kaise request aayegi.
public class CorsConfig {
    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of("http://localhost:5173"));

        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","DELETE"));

//        ye batane ke liye ki frontend se kisi bhi type ki request ya method parameter ke through backend ko as a request aa skta hai.
        corsConfiguration.setAllowedHeaders(List.of("*"));

//        jaise ki hm backend se frontend ko cookies send krnege uske liye.
        corsConfiguration.setAllowCredentials(true);

//        ye batane ke liye ek ye cors wala feature kis endpoint pr applicable hoga.
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();

//        Jo CORS configuration humne banayi hai, use application ke sabhi URL endpoints par apply kar do.
        source.registerCorsConfiguration("/**",corsConfiguration);

        return source;
    }

}
