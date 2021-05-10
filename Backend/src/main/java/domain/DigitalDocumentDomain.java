package domain;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DigitalDocumentDomain {
    private Integer id;
    private String type;
    private Boolean required;
    private String templateLocation;
    private String description;
}
