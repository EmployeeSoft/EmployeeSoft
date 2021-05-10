package domain;

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
    private String cellPhone;
    private String altPhone;
    private Integer gender;
    private String ssn;
    private Date dob;
    private AddressDomain addressDomain;
    private ContactDomain contactDomain;
}
