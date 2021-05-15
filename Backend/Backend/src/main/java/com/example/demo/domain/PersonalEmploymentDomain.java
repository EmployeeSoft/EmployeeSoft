package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalEmploymentDomain {
    private Integer id;
    private String workAuth;
    private String waStart;
    private String waEnd;
    private String employStart;
    private String employEnd;
    private String title;
}
