package com.member.service;

import com.member.model.EncryptResult;
import com.member.model.Member;
import com.member.repository.MemberRepository;
import com.member.utils.EncryptUtils;
import com.member.utils.ValidationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // synchronized 동시성 제어
    public synchronized Member saveMember(Member member) throws Exception {

        // 유효성 검사
        this.isValidate(member);

        // 패스워드 단방향 암호화
        final EncryptResult encryptResult = EncryptUtils.getSha256WithSalt(member.getPassword());
        member.setPassword(encryptResult.getPassword());
        member.setSalt(encryptResult.getSalt()); // TODO : 로그인 구현 시 저장된 salt 값 조회 후 요청 온 비밀번호와 salt 값을 더하여 암호화한 후 비교(레인보우 테이블에 조금 더 안전한 방식)

        // 아이디, 이메일, 휴대폰번호 양방향 암호화
        member.setId(EncryptUtils.getAes256Encrypt(member.getId()));
        member.setEmail(EncryptUtils.getAes256Encrypt(member.getEmail()));
        member.setPhone(EncryptUtils.getAes256Encrypt(member.getPhone()));

        // 회원가입 정보 저장
        return memberRepository.save(member);
    }


    public Member getMember(Long memberNo) throws Exception {
        Member member = memberRepository.findById(memberNo).orElseThrow(() -> new Exception("회원 정보 조회 실패"));
        member.setId(EncryptUtils.getAes256Decrypt(member.getId()));
        member.setEmail(EncryptUtils.getAes256Decrypt(member.getEmail()));
        member.setPhone(EncryptUtils.getAes256Decrypt(member.getPhone()));
        return member;
    }

    private void isValidate(Member member) throws Exception {
        // 회원가입 정보 유효성 검사
        ValidationUtils.memberJoinValidation(member);

        // 아이디 유효성 검사
        ValidationUtils.isIdPattern(member.getId());

        // 비밀번호 유효성 검사
        ValidationUtils.isPasswordPattern(member.getPassword());

        // 아이디 중복 검사(db 조회를 가장 마지막에 검사)
        this.isDuplicatedId(member);
    }

    private void isDuplicatedId(Member member) throws Exception {
        Optional<Member> optionalMember = memberRepository.findById(EncryptUtils.getAes256Encrypt(member.getId()));
        if (optionalMember.isPresent()) throw new IllegalArgumentException("이미 중복된 아이디가 존재합니다.");
    }

}
