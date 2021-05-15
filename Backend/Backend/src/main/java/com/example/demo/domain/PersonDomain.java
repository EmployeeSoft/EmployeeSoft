package com.example.demo.domain;

import lombok.*;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PersonDomain {
    private Integer id;
    private String firstName;
    private String lastName;
    private String middleName;
    private String preferName;
    private String email;
    private String cellPhone;
    private String altPhone;
    private Integer gender;
    private String ssn;
    private String dob;
    private Integer userId;
    private AddressDomain addressDomain;
    private ContactDomain contactDomain;

    @Override
    public String toString() {
        return "PersonDomain{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", preferName='" + preferName + '\'' +
                ", email='" + email + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", altPhone='" + altPhone + '\'' +
                ", gender=" + gender +
                ", ssn='" + ssn + '\'' +
                ", dob=" + dob +
                '}';
    }
}
