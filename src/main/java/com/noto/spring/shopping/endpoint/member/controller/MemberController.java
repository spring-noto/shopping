package com.noto.spring.shopping.endpoint.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
  @RequestMapping("/loginForm")
  public String login() {
    return "shopping/member/loginForm";
  }
}
