package com.member.repository;

import com.member.model.Member;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        member = new Member();
        member.setId("gyyang");
        member.setEmail("ykyconme00@gmail.com");
        member.setPassword("didrlduf11!");
        member.setPhone("010-2708-0663");
    }

    @Test
    void testSaveMember() {
        Member result = memberRepository.save(member);
        assertThat(result.getSeq(), is(1L));
        assertThat(result.getId(), is("gyyang"));
    }

    @Test
    void testGetMember() {
        memberRepository.save(member);
        Optional<Member> result = memberRepository.findById(member.getId());
        assertThat(result.get().getId(), is("gyyang"));
    }

}