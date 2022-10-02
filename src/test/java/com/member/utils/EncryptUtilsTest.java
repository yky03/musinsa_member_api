package com.member.utils;

import com.member.model.EncryptResult;
import org.junit.jupiter.api.Test;
import org.springframework.util.ObjectUtils;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class EncryptUtilsTest {

    @Test
    void testGetSha256Encrypt() throws Exception {
        EncryptResult encryptResult = EncryptUtils.getSha256WithSalt("test1234");
        System.err.println(encryptResult.getPassword());
        System.err.println(encryptResult.getSalt());
        assertThat(true, is(!ObjectUtils.isEmpty(encryptResult.getPassword())));
        assertThat(true, is(!ObjectUtils.isEmpty(encryptResult.getSalt())));
    }

    @Test
    void testGetAes256Encrypt() throws Exception {
        assertThat("+tLz8DzL7VzaZFiRmtl8Og==", is(EncryptUtils.getAes256Encrypt("test1234")));
    }

    @Test
    void testGetAes256Decrypt() throws Exception {
        assertThat("test1234", is(EncryptUtils.getAes256Decrypt("+tLz8DzL7VzaZFiRmtl8Og==")));
    }

}