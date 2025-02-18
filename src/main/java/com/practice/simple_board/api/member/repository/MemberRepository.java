package com.practice.simple_board.api.member.repository;

import com.practice.simple_board.api.member.entity.Member;
import lombok.NonNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findById(@NonNull Long id);

    Optional<Member> findByEmail(String email);

    boolean existsByNickname(String nickname);

}
