package com.too_codemen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.too_codemen.entity.Material;
import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Data
public class CourseRequest {

    private String name;

    private Set<Material> materials;

    private List<Task> tasks;

    private User user;

    private Timestamp startTime;

    private Timestamp finishTime;

    private Timestamp deadline;

    private Boolean status;

    private String audience;
}
