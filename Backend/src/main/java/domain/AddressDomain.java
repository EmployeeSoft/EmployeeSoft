package domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDomain {
    private Integer id;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String zipcode;
    private String stateName;
    private String stateAbbr;
}
