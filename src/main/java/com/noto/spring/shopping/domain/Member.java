package com.noto.spring.shopping.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
public class Member extends BaseEntity {

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

  private int activation = 1;
}
