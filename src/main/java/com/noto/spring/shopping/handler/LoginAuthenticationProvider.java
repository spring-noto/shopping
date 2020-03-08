package com.noto.spring.shopping.handler;

import com.noto.spring.shopping.service.MemberService;
import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private MemberService memberService;

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {

    String userId = authentication.getName();
    String password = (String) authentication.getCredentials();
    System.out.println(userId);
    System.out.println(password);
    UserDetails userDetails;
    Collection<? extends GrantedAuthority> authorities;
    try {
      userDetails = memberService.loadUserByUsername(userId);

      if (!passwordEncoder.matches(password, userDetails.getPassword())) {
        throw new BadCredentialsException("비밀번호가 일치하지 않습니다.");
      }

      authorities = userDetails.getAuthorities();
    } catch (UsernameNotFoundException e) {
      throw new UsernameNotFoundException(e.getMessage());
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException(e.getMessage());
    }
    return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return true;
  }
}
