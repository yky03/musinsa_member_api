package com.member.model;


import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EncryptResult {
    private String password;
    private String salt;
}
