package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration 
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean 
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	    http
	        .authorizeRequests(authorizeRequests ->
	            authorizeRequests
	                .requestMatchers("/admin/signin", "/admin/signup", "/signup/confirm", "/signup/complete", "/signup/register").permitAll()
	                .requestMatchers("/admin/**").authenticated() // '/admin/'以下のすべてのURLに対して認証を必要とします
	        )
	        .formLogin(formLogin ->
	            formLogin
	                .loginPage("/admin/signin")
	                .loginProcessingUrl("/admin/signin") 
	                .usernameParameter("username")
	                .passwordParameter("password")
	                .defaultSuccessUrl("/admin/contacts", true)
	                .failureUrl("/admin/signin?error=true")
	                .permitAll()
	        )
	        .logout(logout ->
            logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutUrl("/logout")
                .logoutSuccessUrl("/admin/signin")
        );
    return http.build();
}
}