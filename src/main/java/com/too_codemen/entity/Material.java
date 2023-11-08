package com.too_codemen.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "materials")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "material_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "audience")
    private String audience;

    @Column(name = "yandex_forms_link")
    private String yandexFormsLink;

    @Column(name = "link")
    private String link;
}
