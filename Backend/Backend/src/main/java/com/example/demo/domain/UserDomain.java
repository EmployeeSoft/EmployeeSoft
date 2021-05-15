package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDomain {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    private Integer userId;
    private String userRole;
}
