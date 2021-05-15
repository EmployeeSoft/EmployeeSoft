package com.example.demo.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalContactDomain {
    private Integer id;
    private String email;
    private String workEmail;
    private String phone;
    private String workPhone;
}
