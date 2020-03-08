package com.noto.spring.shopping.endpoint.member.controller;

import com.noto.spring.shopping.domain.Member;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
  @RequestMapping("/loginForm")
  public String login() {
    return "member/loginForm";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("account", new Member());
    return "member/loginForm";
  }

  @GetMapping("/finish")
  public String finish() {
    return "admin/account/finish";
  }

}
