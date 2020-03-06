package com.noto.spring.shopping.repository;

import com.noto.spring.shopping.domain.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {

  Page<Member> findAllByActivation(int activation, Pageable pageable);
}
