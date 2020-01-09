package com.niit.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailsService userDetailService;
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder()
	  {
	    return new BCryptPasswordEncoder();
	  };
	  
	  @Override
	  protected void configure(AuthenticationManagerBuilder auth) throws Exception 
	  {
	    auth.userDetailsService(userDetailService).passwordEncoder(passwordEncoder());
	  }
	  
	  @Override
	  protected void configure(HttpSecurity http) throws Exception 
	  {
		  http.authorizeRequests()
//		  .antMatchers("/resources/**").permitAll()
//		  .antMatchers("/cartitem/add/**").authenticated()
//		  .antMatchers("/cartitem/display").authenticated()
	       .antMatchers("/user/display").hasAuthority("admin")
//	      .antMatchers("**/https://test.instamojo.com/**").permitAll()
//	      .antMatchers("https://7d6965f7.ngrok.io/**").permitAll()
	      //.antMatchers("/**").hasAnyRole("ADMIN", "USER")
	      .and().formLogin()
	      
	      .defaultSuccessUrl("/").failureUrl("/login")
	      .loginPage("/login").loginProcessingUrl("/loginaction").permitAll()
	      .and()
	      .logout().logoutUrl("/logout")
	      .invalidateHttpSession(true)
	      .clearAuthentication(true)
	      .deleteCookies("JESSIONID")
	      .logoutSuccessUrl("/")
	      .and().csrf().disable();
	  }
}
