package com.too_codemen.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.too_codemen.RoleConverter;
import lombok.Data;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
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

    @Column(name = "completed_tasks")
    private Long completedTasks;

    @Column(name = "failed_tasks")
    private Long failedTasks;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "finish_time")
    private Timestamp finishTime;

    @Column(name = "role")
    @Convert(converter = RoleConverter.class)
    private Role role;

    @Column(name = "primary_onboarding")
    private Boolean primaryOnboarding;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "curator_id")
    private Curator curator;

}
