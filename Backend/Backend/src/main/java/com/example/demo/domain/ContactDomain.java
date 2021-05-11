package com.example.demo.domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContactDomain {
    private Integer id;
    private String relationship;
    private String title;
    private Boolean isReference;
    private Boolean isEmergency;
    private Boolean isLandlord;
}
