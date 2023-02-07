package jpabook.jpashop.controller;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Getter @Setter
public class MemberForm {
    @NotEmpty(message = "회원 이름은 필수입니다.")
    private String name;
    @NotBlank(message = "도시를 입력해주세요")
    private String city;
    @NotBlank(message = "거리를 입력해주세요")
    private String street;
    @NotBlank(message = "우편번호를 입력해주세요")
    private String zipcode;
}
