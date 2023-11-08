package com.too_codemen.entity;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private Set<Material> materials;

    @OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
    private List<Task> tasks;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "start_time")
    private Timestamp startTime;

    @Column(name = "finish_time")
    private Timestamp finishTime;

    @Column(name = "deadline")
    private Timestamp deadline;

    @Column(name = "status")
    private Boolean status;

    @Column(name = "audience")
    private String audience;
}

