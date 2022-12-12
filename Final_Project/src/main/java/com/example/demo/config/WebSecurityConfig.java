package com.example.demo.config;

<<<<<<< HEAD
=======
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
>>>>>>> 68e359216f359b0d1b18adba896a8ae0f3bd1f5b
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
<<<<<<< HEAD
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
=======
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
>>>>>>> 68e359216f359b0d1b18adba896a8ae0f3bd1f5b

import com.example.demo.service.CustomUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Autowired
	private DataSource dataSource;
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/home").authenticated()
			.anyRequest().permitAll()
			.and()
			.formLogin().loginPage("/login")
				.usernameParameter("username")
				.defaultSuccessUrl("/home")
				.permitAll()
				.failureForwardUrl("/fail_login")
			.and()
<<<<<<< HEAD
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login");
=======
			.logout().logoutSuccessHandler(new LogoutSuccessHandler() {
				 
                public void onLogoutSuccess(HttpServletRequest request,
                            HttpServletResponse response, Authentication authentication)
                        throws IOException, ServletException {
                	System.out.println("This user logged out: " + authentication.getName());
                	
                    response.sendRedirect("/logout");
                }
            })
            .permitAll();
>>>>>>> 68e359216f359b0d1b18adba896a8ae0f3bd1f5b
		
		return http.build();
	}
	
	
}
