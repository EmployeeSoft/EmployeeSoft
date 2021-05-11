package com.example.demo.entity;

import lombok.*;
import org.hibernate.boot.model.naming.ImplicitNameSource;

import javax.persistence.*;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="app_workflow", schema = "backend")
public class ApplicationWorkFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "employee_id")
    private Integer employeeId;

    @ManyToOne
    @JoinColumn(name = "employee_id" , insertable = false, updatable = false)
    private Employee employee;

    @Column(name = "date_created")
    private Date dateCreated;

    @Column(name = "date_modified")
    private Date dateModified;

    @Column(name = "status")
    private Integer status;

    @Column(name = "comment")
    private String comment;

    @Column(name = "type")
    private String type;

    @Override
    public String toString() {
        return "ApplicationWorkFlow{" +
                "id=" + id +
                ", employeeId=" + employeeId +
                ", dateCreated=" + dateCreated +
                ", dateModified=" + dateModified +
                ", status=" + status +
                ", comment='" + comment + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
