package com.member.controller;

import com.google.gson.Gson;
import com.member.model.Member;
import com.member.service.MemberService;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
@AutoConfigureMockMvc
class MemberControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MemberController memberController;

    @Autowired
    private WebApplicationContext wac;

    @Mock
    private MemberService memberService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        ReflectionTestUtils.setField(memberController, "memberService", memberService);
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @DisplayName("회원가입 컨트롤러 테스트")
    @Test
    void saveMemberControllerTest() throws Exception {
        Member member = new Member();
        member.setId("gyyang");
        member.setEmail("ykyconme00@gmail.com");
        member.setPassword("didrlduf11!");
        member.setPhone("010-2708-0663");
        String jsonData = new Gson().toJson(member);

        mockMvc.perform(MockMvcRequestBuilders.post("/members")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonData))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @DisplayName("회원정보 조회 컨트롤러 테스트")
    @Test
    void getMemberControllerTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/members/1"))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

}