package com.Usuarios.common.configurations.beans;

import com.Usuarios.usuarios.domain.ports.in.RoleServicePort;
import com.Usuarios.usuarios.domain.ports.in.UserServicePort;
import com.Usuarios.usuarios.domain.ports.out.RolePersistencePort;
import com.Usuarios.usuarios.domain.ports.out.UserPersistencePort;
import com.Usuarios.usuarios.domain.usecases.RoleUseCase;
import com.Usuarios.usuarios.domain.usecases.UserUseCase;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.RolePersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.adapters.persistence.mysql.UserPersistenceAdapter;
import com.Usuarios.usuarios.infrastructure.mappers.RoleEntityMapper;
import com.Usuarios.usuarios.infrastructure.mappers.UserEntityMapper;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.RoleRepository;
import com.Usuarios.usuarios.infrastructure.repositories.mysql.UserRepository;
import com.Usuarios.usuarios.infrastructure.security.CustomUserDetailsService;
import com.Usuarios.usuarios.infrastructure.security.JwtAuthenticationFilter;
import com.Usuarios.usuarios.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableMethodSecurity

public class BeanConfiguration {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleRepository roleRepository;
    private final RoleEntityMapper roleEntityMapper;

    @Bean
    public UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort(), roleUseCase());
    }

    @Bean
    public UserPersistencePort userPersistencePort() {
        return new UserPersistenceAdapter(userRepository, userEntityMapper);
    }

    @Bean
    public RolePersistencePort rolePersistencePort() {
        return new RolePersistenceAdapter(roleRepository, roleEntityMapper);
    }

    @Bean
    public RoleServicePort roleServicePort() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public RoleUseCase roleUseCase() {
        return new RoleUseCase(rolePersistencePort());
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/v1/auth/login", "/api/v1/user/buyer", "/api/v1/user/seller", "/api/v1/user/admin").permitAll()
                        .anyRequest().authenticated()
                );
        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        http.setSharedObject(CorsConfigurationSource.class, corsConfigurationSource());


        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        return new JwtAuthenticationFilter(jwtUtil, customUserDetailsService);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("http://localhost:4200"));
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}