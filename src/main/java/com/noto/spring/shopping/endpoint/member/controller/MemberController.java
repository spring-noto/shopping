package com.noto.spring.shopping.endpoint.member.controller;

import com.noto.spring.shopping.domain.Member;
import com.noto.spring.shopping.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/member")
public class MemberController {

  @Autowired
  MemberRepository memberRepository;

  @Autowired
  PasswordEncoder passwordEncoder;

  @RequestMapping("/loginForm")
  public String login() {
    return "shopping/member/loginForm";
  }

  @GetMapping("/register")
  public String register(Model model) {
    model.addAttribute("member", new Member());
    return "shopping/member/register";
  }

  @GetMapping("/finish")
  public String finish() {
    return "shopping/member/finish";
  }

  @PostMapping("/registerProcessing")
  public String registerProcessing(@ModelAttribute @Valid Member member, BindingResult bindingResult) {
    System.out.println("??");
    if (bindingResult.hasErrors()) {
      System.out.println("error?");
      return "shopping/member/register";
    }

    Optional<Member> srchMember = memberRepository.findById(member.getId());

    if(srchMember.isEmpty()) {
      member.setPassword(passwordEncoder.encode(member.getPassword()));
      memberRepository.save(member);
      System.out.println("save");
    } else {
      System.out.println("error");
      return "redirect:/member/register?error";
    }
    return "redirect:/member/finish?register";
  }

}
