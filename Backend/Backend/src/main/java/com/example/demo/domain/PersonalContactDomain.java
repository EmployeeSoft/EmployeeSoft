package com.example.demo.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalContactDomain {
    private Integer person_id;
    private String full_name;
    private String phone;
    private String relationship;
    private String title;
    private String address;
    private boolean is_reference;
    private boolean is_emergency;
    private boolean is_landlord;
}
