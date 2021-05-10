package entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="person", schema = "backend")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "middle_name")
    private String middleName;

    @Column(name = "email")
    private String email;

    @Column(name = "cell_phone")
    private String cellPhone;

    @Column(name = "alt_phone")
    private String altPhone;

    @Column(name = "gender")
    private Integer gender;

    @Column(name = "ssn")
    private String ssn;

    @Column(name = "dob")
    private Date dob;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private User user;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Contact contact;

    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Employee employee;
}
