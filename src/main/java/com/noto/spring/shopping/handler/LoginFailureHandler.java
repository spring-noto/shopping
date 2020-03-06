package com.noto.spring.shopping.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@Slf4j
public class LoginFailureHandler implements AuthenticationFailureHandler {

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException exception) throws IOException, ServletException {

    String id = request.getParameter("id");
    String password = request.getParameter("password");
    String defaultFailureUrl =  "/member/loginForm?error";
    String errormsg = null;

    request.setAttribute("id", id);
    request.setAttribute("password", password);

    if(exception instanceof UsernameNotFoundException) {
      errormsg = exception.getMessage();
    } else if (exception instanceof BadCredentialsException) {
      errormsg = exception.getMessage();
    } else {
      errormsg = exception.getMessage();
    }

    request.setAttribute("errormsg", errormsg);

    request.getRequestDispatcher(defaultFailureUrl).forward(request, response);
  }
}
