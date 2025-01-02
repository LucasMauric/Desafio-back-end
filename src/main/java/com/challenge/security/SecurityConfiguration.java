package com.challenge.security;

import com.challenge.service.impl.AuthorizationServiceImpl;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
@EnableWebSecurity
@SecurityScheme(name = SecurityConfiguration.SECUTIRY,type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "Bearer")
public class SecurityConfiguration {
    public static final String SECUTIRY = "bearerAuth ";
    @Autowired
    private AuthorizationServiceImpl authorizationService;
    @Autowired
    CorsConfig config;
    @Bean
    public SecurityFilter authenticationJwtTokenFilter(){
        return new SecurityFilter();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

         httpSecurity.csrf(AbstractHttpConfigurer::disable)
                 .cors(cors -> cors.configurationSource(config.corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                                .requestMatchers(HttpMethod.POST,"/transfer/**" ).hasAnyRole("USER")
                                .requestMatchers(HttpMethod.POST, "/auth/register/**").permitAll()
                                .requestMatchers(HttpMethod.POST, "/auth/login/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/findAll/**").permitAll()
                                .requestMatchers("/swagger-ui/**","/v3/api-docs/**","/swagger-ui.html/**").permitAll()
                                .anyRequest().authenticated()
                );
        httpSecurity.authenticationProvider(AuthenticationProvider());
        httpSecurity.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
    @Bean
    public DaoAuthenticationProvider AuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(authorizationService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
