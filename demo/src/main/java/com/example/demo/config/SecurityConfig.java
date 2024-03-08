package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/sign").permitAll()
				.requestMatchers("/signup").permitAll()
				.requestMatchers("/", "/home").permitAll()
				.requestMatchers("/sendemail/**").permitAll()
				.requestMatchers("/ordersuccessful").permitAll()
				.requestMatchers("/detail", "/CSS/**", "/images/**", "/js/**", "/vendor/**").permitAll()
				.requestMatchers("/addcart").authenticated()
				.anyRequest().authenticated()
				
			)
			
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
				.usernameParameter("username")
				.passwordParameter("pass")
				.defaultSuccessUrl("/home", true)
				.failureUrl("/login?success=false")
			)
			.logout((logout) -> logout
                    .logoutSuccessUrl("/login?logoutSuccess=true")
                    .deleteCookies("JSESSIONID")
			);

		return http.build();
	}
	
}
