package com.practice.simple_board.api.member.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Builder
public class MemberRegisterDTO {
    private String email;
    private String password;
    private String nickname;
}
