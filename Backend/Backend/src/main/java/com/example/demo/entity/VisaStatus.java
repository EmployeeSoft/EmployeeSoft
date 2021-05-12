package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.sql.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="visa_status", schema = "backend")
public class VisaStatus implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "visa_type")
    private String visaType;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "date_modified")
    private Date dateModified;

    @Column(name = "create_user")
    private String createUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "visaStatus", cascade = CascadeType.MERGE)
    List<Employee> employees = new ArrayList<>();

    @Override
    public String toString() {
        return "VisaStatus{" +
                "id=" + id +
                ", visaType='" + visaType + '\'' +
                ", isActive=" + isActive +
                ", dateModified=" + dateModified +
                ", createUser='" + createUser + '\'' +
                '}';
    }
}
