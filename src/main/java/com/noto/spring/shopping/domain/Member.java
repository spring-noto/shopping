package com.noto.spring.shopping.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Data
@Entity
@EntityListeners(AuditingEntityListener.class) //auditing 기능 사용
public class Member {

  @Id
  @NotBlank(message = "ID를 입력해주세요")
  @Size(min = 6, max = 15, message = "ID는 6 ~ 15자 사이로 입력해주세요")
  private String id;

  @NotBlank(message = "비밀번호를 입력해주세요")
  private String password;

  @NotBlank(message = "이름을 입력해주세요")
  private String name;

  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "이메일 형식을 맞춰주세요")
  private String email;

  private String address;

  private Boolean activation = true;

  @CreatedDate
  private LocalDateTime created_at;

  @LastModifiedDate
  private LocalDateTime updated_at;
}
