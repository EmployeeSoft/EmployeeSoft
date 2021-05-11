package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="registration_token", schema = "authentication")
public class RegistrationToken implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "token")
    private String token;

    @Column(name = "start_time")
    private Date startTime;

    @Column(name = "end_time")
    private Date endTime;

    @Column(name = "email")
    private String email;

    @Column(name = "created_by")
    private String createdBy;

<<<<<<< HEAD:Backend/AuthServer/src/main/java/com/example/demo/entity/RegistrationToken.java
    //Remove the relationship because name="email" is repeated
//    @OneToOne
//    @JoinColumn(name = "email")
//    private User user;
=======
    @OneToOne
    @JoinColumn(name = "email", insertable = false, updatable = false)
    private User user;
>>>>>>> eb9e0aaf826cf8645d51b999e902ffc00040a823:Backend/AuthServer/src/main/java/entity/RegistrationToken.java
}
