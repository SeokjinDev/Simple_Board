package com.practice.simple_board.api.member.dto;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MemberUpdateDTO {
    private String email;
    private String password;
    private String nickname;
}
