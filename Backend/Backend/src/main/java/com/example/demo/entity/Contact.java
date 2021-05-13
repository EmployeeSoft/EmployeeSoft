package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="contact", schema = "backend")
public class Contact implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "person_id")
    private Integer personId;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "phone")
    private String phone;

    @Column(name = "relationship")
    private String relationship;

    @Column(name = "title")
    private String title;

    @Column(name = "address")
    private String address;

    @Column(name = "is_reference")
    private Boolean isReference;

    @Column(name = "is_emergency")
    private Boolean isEmergency;

    @Column(name = "is_landlord")
    private Boolean isLandlord;

    @ManyToOne
    @JoinColumn(name = "person_id", insertable = false, updatable = false)
    private Person person;

    @Override
    public String toString() {
        return "Contact{" +
                "id=" + id +
                ", personId=" + personId +
                ", fullName='" + fullName + '\'' +
                ", phone='" + phone + '\'' +
                ", relationship='" + relationship + '\'' +
                ", title='" + title + '\'' +
                ", address='" + address + '\'' +
                ", isReference=" + isReference +
                ", isEmergency=" + isEmergency +
                ", isLandlord=" + isLandlord +
                '}';
    }
}
