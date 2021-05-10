package entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="contact")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "title")
    private String title;

    @Column(name = "is_reference")
    private Boolean isReference;

    @Column(name = "is_emergency")
    private Boolean isEmergency;

    @Column(name = "is_landlord")
    private Boolean isLandlord;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;
}
