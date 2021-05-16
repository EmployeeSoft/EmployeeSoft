package com.example.demo.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalContactDomain {
    private Integer person_id;
    private ContactDomain[] emergency;
}
