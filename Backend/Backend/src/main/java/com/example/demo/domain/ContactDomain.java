package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDomain {
    private String fullName;
    private String phone;
    private String relationship;
    private String address;
    private String title;
    private Boolean isReference;
    private Boolean isEmergency;
    private Boolean isLandlord;
}
