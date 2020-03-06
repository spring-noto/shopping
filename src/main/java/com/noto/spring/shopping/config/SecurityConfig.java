package com.noto.spring.shopping.config;

import com.noto.spring.shopping.handler.LoginFailureHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  public LoginFailureHandler loginFailureHandler;


  @Override
  public void configure(WebSecurity web) throws Exception {
    web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/templates/**", "/webjars/**");

  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {

    http.csrf().disable();

    http.authorizeRequests()
      .antMatchers("/css/**", "/js/**", "/images/**", "/resources/**", "/webjars/**").permitAll();

    http.authorizeRequests()
      .anyRequest().permitAll();

    http.formLogin()
      .loginPage("/member/loginForm")
      .loginProcessingUrl("/authenticate")
      .defaultSuccessUrl("/")
      .usernameParameter("id")
      .passwordParameter("password")
      .failureHandler(loginFailureHandler)
      .permitAll();

    http.logout()
      .logoutUrl("/logout")
      .logoutSuccessUrl("/member/loginForm")
      .permitAll();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return PasswordEncoderFactories.createDelegatingPasswordEncoder();
  }
}
