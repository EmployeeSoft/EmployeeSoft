package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VisaStatusRequestDomain {
    private Integer userId;
    private String userRole;
}
