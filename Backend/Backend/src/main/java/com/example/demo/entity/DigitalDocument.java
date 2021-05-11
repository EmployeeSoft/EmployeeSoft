package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name="digital_doc", schema = "backend")
public class DigitalDocument {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "type")
    private String type;

    @Column(name = "required")
    private Boolean required;

    @Column(name = "template_location")
    private String templateLocation;

    @Column(name = "description")
    private String description;

    @Override
    public String toString() {
        return "DigitalDocument{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", required=" + required +
                ", templateLocation='" + templateLocation + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
