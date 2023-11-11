package com.too_codemen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.too_codemen.RoleConverter;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "curators")
public class Curator {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "curator_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "patronymic")
    private String patronymic;

    @Column(name = "password")
    private String password;

    @Column(name = "position")
    private String position;

    @Column(name = "email")
    private String email;

    @Column(name = "number")
    private String number;

    @Column(name = "telegram")
    private String telegram;

    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "vk_group_id")
    private Long vkGroupId;

    @OneToMany(mappedBy = "curator", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<User> users;

}
