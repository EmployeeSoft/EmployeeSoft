package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

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

    @Column(name = "user_id")
    private Integer userId;

//    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
//    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Contact> contacts;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "person")
    private List<Address> addressList;

    @OneToOne(fetch = FetchType.LAZY, mappedBy = "person")
    private Employee employee;

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", middleName='" + middleName + '\'' +
                ", email='" + email + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", altPhone='" + altPhone + '\'' +
                ", gender=" + gender +
                ", ssn='" + ssn + '\'' +
                ", dob=" + dob +
                ", userId=" + userId +
//                ", address=" + address +
                '}';
    }
}
