package com.mindhub.todolist.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private final SecurityFilter securityFilter;
    private final UserDetailsService userDetailsService;

    public SecurityConfiguration(SecurityFilter securityFilter, UserDetailsService userDetailsService) {
        this.securityFilter = securityFilter;
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(sesion -> sesion.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        //Autenticacion Controller
                        .requestMatchers(HttpMethod.POST, "/api/login/**").permitAll()
                        //Register Controller
                        .requestMatchers(HttpMethod.POST, "/api/admin/register", "/api/client/register").permitAll()
                        //Admin Controllers
                        .requestMatchers(HttpMethod.PATCH,"/api/admin/update").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/admin/delete/{id}").hasAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/admin/existUserEntity/{id}").hasAuthority("ADMIN")
                        //Client Controllers
                        .requestMatchers(HttpMethod.PATCH,"/api/client/update").hasAuthority("USER")
                        .requestMatchers(HttpMethod.DELETE,"/api/client/delete/{id}").hasAuthority("USER")
                        .requestMatchers(HttpMethod.GET,"/api/client/existUserEntity/{id}").hasAuthority("USER")
                        //Task Controllers
                        .requestMatchers(HttpMethod.POST,"/api/task/create").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/task/getAll").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/client/countUserTasks/{id}").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.PATCH,"/api/task/update").hasAnyAuthority("USER", "ADMIN")
                        .requestMatchers(HttpMethod.DELETE,"/api/task/delete/{id}").hasAnyAuthority("USER", "ADMIN")
                        //User Controllers
                        .requestMatchers(HttpMethod.GET,"/api/user/getAll").hasAnyAuthority("ADMIN")
                        .requestMatchers(HttpMethod.GET,"/api/user/findUserTasks/{id}").hasAnyAuthority("USER", "ADMIN")
                        //Swagger
                        .requestMatchers(HttpMethod.GET,"/swagger-ui.html", "/v3/api-docs/**", "/swagger-ui/**").permitAll()
                        //H2 Database
                        .requestMatchers("/h2-console/**").permitAll()
                        .anyRequest().denyAll())
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) //Resuelve el error de conexion a H2
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .userDetailsService(userDetailsService)
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
