package com.example.demo.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalAddressDomain {
    private Integer person_id;
    private String address_line_1;
    private String address_line_2;
    private String city;
    private String zipcode;
    private String state_name;
    private String state_abbr;
}
