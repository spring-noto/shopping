package com.noto.spring.shopping.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.noto.spring.shopping.domain.Member;
import com.noto.spring.shopping.repository.MemberRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MemberService implements UserDetailsService {

  @Autowired
  private MemberRepository memberRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Override
  public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
    Optional<Member> byUsername = memberRepository.findById(userId);
    Member member = byUsername.orElseThrow(() -> new UsernameNotFoundException("사용자 ID가 존재하지 않습니다."));
    if (member.getActivation() == 0) {
      throw new UsernameNotFoundException(userId + " : 현재 휴먼계정입니다.");
    }
    List<GrantedAuthority> authorities = new ArrayList<>();

    return new User(member.getId(), member.getPassword(), authorities);
  }

  public List<Member> convertMapToAccount(List<Map<String, Object>> maps) {
    final ObjectMapper objectMapper = new ObjectMapper()
      .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    List<Member> accountList = new ArrayList<>();
    maps.forEach(map -> {
      accountList.add(objectMapper.convertValue(map, Member.class));
    });
    return accountList;
  }

  public Page<Member> getAccountListByUseYn(int useYn, Pageable pageable) {
    return memberRepository.findAllByActivation(useYn, pageable);
  }

  public void saveAccountList(List<Map<String, Object>> accountList) {
    memberRepository.saveAll(convertMapToAccount(accountList));
  }

}
