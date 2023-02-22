package com.bolsadeideas.springboot.datajpa.app;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.expression.WebExpressionAuthorizationManager;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {

  @Bean
  // authentication
  public UserDetailsService userDetailsService(PasswordEncoder encoder) {
    UserDetails admin =
        User.withUsername("admin").password(encoder.encode("admin")).roles("ADMIN").build();

    UserDetails user =
        User.withUsername("user").password(encoder.encode("user")).roles("USER").build();

    return new InMemoryUserDetailsManager(admin, user);
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf()
        .disable()
        .authorizeHttpRequests(
            authorize ->
                authorize
                    .requestMatchers("/", "/css/**", "/js/**", "/images/**", "/listar")
                    .permitAll()
                    .requestMatchers("/ver/**")
                    .access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                    .requestMatchers("/uploads/**")
                    .access(new WebExpressionAuthorizationManager("hasRole('USER')"))
                    .requestMatchers("/form/**")
                    .access(new WebExpressionAuthorizationManager("hasRole('ADMIN')"))
                    .requestMatchers("/eliminar/**")
                    .access(new WebExpressionAuthorizationManager("hasRole('ADMIN')"))
                    .requestMatchers("/factura/**")
                    .access(new WebExpressionAuthorizationManager("hasRole('ADMIN')"))
                    .anyRequest()
                    .authenticated())
        .formLogin()
        .loginPage("/login")
        .permitAll()
        .and()
        .logout()
        .permitAll()
        .and()
        .httpBasic();

    return http.build();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
