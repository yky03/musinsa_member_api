package com.member.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class ValidationUtilsTest {

    @Test
    void testSuccessValidation() {
        ValidationUtils.isIdPattern("yky03");
        ValidationUtils.isPasswordPattern("didrlduf11!");
        ValidationUtils.isValidEmailAddress("ykycome00@gmail.com");
        ValidationUtils.isValidPhoneNumber("010-2708-0663");
    }

    @Test
    void testIdFailValidation() {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            ValidationUtils.isIdPattern("!@#$");
        });
        assertThat("아이디는 5~20자 사이의 영어 소문자 혹은 숫자만의 조합을 사용해주세요.", is(exception.getMessage()));
    }

    @Test
    void testPasswordFailValidation() {
        Throwable exception = assertThrows(RuntimeException.class, () -> {
            ValidationUtils.isPasswordPattern("12345");
        });
        assertThat("비밀번호는 8~20자 사이의 영어대소문자, 숫자, 특수문자 조합중 최소 2가지 조합을 사용해주세요.", is(exception.getMessage()));
    }

    @Test
    void testFailEmailValidation() {
        ValidationUtils.isValidEmailAddress("ykycome00gmail.com");
        assertThat(false, is(ValidationUtils.isValidEmailAddress("ykycome00gmail.com")));
    }

    @Test
    void testFailPhoneValidation() {
        assertThat(false, is(ValidationUtils.isValidPhoneNumber("01012345.com")));
    }

}