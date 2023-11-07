package com.too_codemen.dto;

import com.too_codemen.entity.Task;
import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDTO {

    private String name;

    private String description;

    private Boolean status;

    private Timestamp timeOfCreation;

    private Timestamp deadline;

    private Long userId;

    public static TaskDTO toModel(Task entity) {
        TaskDTO model = new TaskDTO();
        model.setName(entity.getName());
        model.setDescription(entity.getDescription());
        model.setStatus(entity.getStatus());
        model.setTimeOfCreation(entity.getTimeOfCreation());
        model.setDeadline(entity.getDeadline());
//        model.setUserId(entity.getUserId());
        return model;
    }
}
