package com.sunny.security;



import com.sunny.jwt.JWTAuthenticationFilter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityFilterChainConfig {

    private final AuthenticationProvider authenticationProvider;
    private final JWTAuthenticationFilter jwtAuthenticationFilter;


    public SecurityFilterChainConfig(AuthenticationProvider authenticationProvider,
                                     JWTAuthenticationFilter jwtAuthenticationFilter){
        this.authenticationProvider = authenticationProvider;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;

    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf->csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeRequests()
                .requestMatchers(
                        HttpMethod.POST,
                        "/api/v1/add/customer",
                        "/api/v1/auth/login"
                )
                .permitAll()
                .requestMatchers(
                        HttpMethod.GET,
                        "/ping",
                        "/api/v1/customers/*/profile-image"
                )
                .permitAll()
                .requestMatchers(HttpMethod.GET, "/actuator/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement(sess->sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );
        return http.build();
    }

}
