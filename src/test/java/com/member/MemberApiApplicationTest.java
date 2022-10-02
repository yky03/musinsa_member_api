package com.member;

import com.member.model.Member;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class MemberApiApplicationTest {

    @Test
    @DisplayName("spring boot context load test with h2")
    void contextLoads() {
    }

    @Test
    @DisplayName("스프링 부트 통합 테스트")
    void test_SpringBoot_h2_junit5_assert() {
        Member member = new Member();
        member.setId("양기열");
        String id = "양기열";
        assertThat(member.getId(), is(id));
    }

}
