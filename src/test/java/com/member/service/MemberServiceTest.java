package com.member.service;

import com.member.model.Member;
import com.member.utils.EncryptUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    private MemberService memberService;

    private Member member, member2;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setId("gyyang");
        member.setEmail("ykyconme00@gmail.com");
        member.setPassword("didrlduf11!");
        member.setPhone("010-2708-0663");

        member2 = new Member();
        member2.setId("gyyang2");
        member2.setEmail("ykyconme002@gmail.com");
        member2.setPassword("didrlduf112!");
        member2.setPhone("010-2708-0664");
    }

    @DisplayName("회원가입 테스트")
    @Test
    void testSaveMember() throws Exception {
        Member result = memberService.saveMember(member);
        assertThat(result.getId(), is(member.getId()));
    }

    @DisplayName("아이디 중복 가입 실패 테스트")
    @Test
    void testSaveMemberDuplicatedId() throws Exception {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            memberService.saveMember(member);
        });
        assertThat("이미 중복된 아이디가 존재합니다.", is(exception.getMessage()));
    }

    @DisplayName("복호화 후 아이디 정보 조회 비교")
    @Test
    void testGetMember() throws Exception {
        memberService.saveMember(member2); // member2 암호화된 객체 정보 엔티티로 바뀜
        Member result = memberService.getMember(2L);
        assertThat(result.getId(), is(EncryptUtils.getAes256Decrypt(member2.getId())));
    }

}