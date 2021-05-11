package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="user", schema = "authentication")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @OneToOne
    @JoinColumn(name = "person_id")
    private Person person;

    //Remove the relationship because name="email" is repeated
//    @OneToOne
//    @JoinColumn(name = "email")
//    private RegistrationToken registrationToken;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.MERGE)
    List<UserRole> userRoles = new ArrayList<>();
}
