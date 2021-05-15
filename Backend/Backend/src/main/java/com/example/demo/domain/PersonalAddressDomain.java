package com.example.demo.domain;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonalAddressDomain {
    private Integer id;
    private String primaryAddress;
    private String secondAddress;
}
