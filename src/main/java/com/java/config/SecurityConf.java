package com.java.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import jakarta.servlet.http.HttpSession;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class SecurityConf {

	@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.logout(logout -> {
			logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET"));
			logout.invalidateHttpSession(true);
			logout.clearAuthentication(true);
			logout.logoutSuccessUrl("/");
			logout.permitAll();	
		});
		http.formLogin(form -> {
			form.loginPage("/signIn");
			form.loginProcessingUrl("/signIn");
			form.usernameParameter("email");
			form.passwordParameter("pwd");
			form.successHandler((req, res, auth) -> {
				HttpSession session = req.getSession();
				session.setAttribute("user", auth.getPrincipal());
				res.sendRedirect("/");
			});
			form.failureHandler((req, res, exc) -> {
				res.sendRedirect("/");
			});
			form.permitAll();
		});
		http.authorizeHttpRequests(req -> {
			req.requestMatchers("/", "/signUp").permitAll();
			req.requestMatchers("/webjars/**").permitAll();
			req.anyRequest().authenticated();
		});
		return http.build();
	}
	
}
