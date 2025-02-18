package com.practice.simple_board.api.member.service;

import com.practice.simple_board.api.member.dto.MemberInfoDTO;
import com.practice.simple_board.api.member.dto.MemberLoginDTO;
import com.practice.simple_board.api.member.dto.MemberRegisterDTO;
import com.practice.simple_board.api.member.dto.MemberUpdateDTO;
import com.practice.simple_board.api.member.entity.Member;
import com.practice.simple_board.api.member.entity.Role;
import com.practice.simple_board.api.member.repository.MemberRepository;
import com.practice.simple_board.common.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public void register(MemberRegisterDTO memberRegisterDTO) {
        Member newMember = Member.builder()
                .email(memberRegisterDTO.getEmail())
                .password(memberRegisterDTO.getPassword())
                .nickname(memberRegisterDTO.getNickname())
                .role(Role.USER)
                .build();

        validateEmail(newMember.getEmail());
        validateNickname(newMember.getNickname());
        memberRepository.save(newMember);
    }

    private void validateEmail(String email) {
        memberRepository.findByEmail(email)
                .ifPresent(member -> {
                    throw new IllegalArgumentException("존재하는 이메일입니다: " + email);
                });
    }

    private void validateNickname(String nickname) {
        if (memberRepository.existsByNickname(nickname)) {
            throw new IllegalArgumentException("존재하는 닉네임입니다: " + nickname);
        }
    }

    @Transactional
    public MemberInfoDTO memberInfo(String email) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("잘못된 이메일입니다"));
        return MemberInfoDTO.builder()
                .email(member.getEmail())
                .nickname((member.getNickname()))
                .build();
    }

    @Transactional
    public void memberUpdate(String email, MemberUpdateDTO memberUpdateDTO) {
        Member member = memberRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));

        Member updatedMember = member.toBuilder()
                .email(memberUpdateDTO.getEmail())
                .password(memberUpdateDTO.getPassword())
                .nickname((member.getNickname()))
                .build();

        memberRepository.save(updatedMember);
    }

    @Transactional
    public void memberDelete(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자입니다."));

        memberRepository.delete(member);
    }
}
