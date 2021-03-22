package com.example.bookstore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.bookstore.web.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
    @Autowired
    private UserDetailServiceImpl userDetailsService;	

	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests().antMatchers("/css/**").permitAll() // css stays when logged out
			.and()
			.authorizeRequests().antMatchers("/signup", "/saveuser").permitAll()
			.and()
			.authorizeRequests().anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login")	// create login page
			.defaultSuccessUrl("/booklist", true)	// define landing page after login
			.permitAll()
			.and()
		.logout()
			.permitAll();	// Allow all users to log out
	}
	
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
	
}
