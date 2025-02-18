package com.practice.simple_board.api.member.controller;

import com.practice.simple_board.api.member.dto.MemberInfoDTO;
import com.practice.simple_board.api.member.dto.MemberLoginDTO;
import com.practice.simple_board.api.member.dto.MemberRegisterDTO;
import com.practice.simple_board.api.member.dto.MemberUpdateDTO;
import com.practice.simple_board.api.member.service.MemberService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/member")
@Tag(name = "Member Controller", description = "회원 관리 API입니다.")
public class MemberController {

    private final MemberService memberService;

    @Operation(
            summary = "Member Register API",
            description = "회원 가입 API 입니다."
    )
    @PostMapping("/register")
    public HttpStatus registerMember(@RequestBody MemberRegisterDTO memberRegisterDTO) {
        memberService.register(memberRegisterDTO);

        return HttpStatus.OK;
    }
    
    @Operation(
            summary = "Member Information API",
            description = "회원 정보 조회 API 입니다."
    )
    @GetMapping("/memberInfo")
    public MemberInfoDTO memberInfo(@RequestBody String email) {
        return memberService.memberInfo(email);
    }
}
