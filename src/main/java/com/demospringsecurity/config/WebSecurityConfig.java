package com.demospringsecurity.config;

import com.demospringsecurity.services.UserDetailsServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.rememberme.InMemoryTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	UserDetailsServicesImpl userDetailsServices;
	@Autowired
	DataSource dataSource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder(){
		//mã hóa password bằng BCrypt
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		return encoder;
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
				//mot so khai niem https://techmaster.vn/posts/36312/spring-security-ban-sau-ve-authentication-va-authorization-p2
				/*
				link tham khao spring security
					https://www.youtube.com/watch?v=TEtm-mMvfBk
					https://www.youtube.com/watch?v=Wvs6Ag8LTwg
					https://www.youtube.com/watch?v=5DYwEoq9WJU&t=1411s
					https://www.youtube.com/watch?v=A2qN9pMR3MQ
					https://www.youtube.com/watch?v=DatkpsipMWI
				*/

		http
			.authorizeRequests()
				.antMatchers("/*", "/home", "/")
				.permitAll()
				.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/user/**").access("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
				.and()
			.formLogin()
				.loginPage("/login")
				.loginProcessingUrl("/j_spring_security_check")
				.permitAll()
				.defaultSuccessUrl("/home")
				.failureUrl("/login?login=fail")
				.and()
			.exceptionHandling()
				.accessDeniedPage("/403")
				.and()
			.logout()
				.permitAll();
		http.authorizeRequests()
				.and()
				.rememberMe()
				.tokenRepository(persistentTokenRepository())
				.tokenValiditySeconds(1 * 24 * 60 * 60);
	}

	@Bean
	public PersistentTokenRepository persistentTokenRepository(){
		InMemoryTokenRepositoryImpl memory = new InMemoryTokenRepositoryImpl();
		return memory;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServices).passwordEncoder(passwordEncoder());
	}
}