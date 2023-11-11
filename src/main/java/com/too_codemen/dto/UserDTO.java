package com.too_codemen.dto;

import com.too_codemen.entity.Role;
import com.too_codemen.entity.Task;
import com.too_codemen.entity.User;
import lombok.Data;

import java.sql.Timestamp;
import java.util.List;

@Data
public class UserDTO {

    private String name;

    private String surname;

    private String patronymic;

    private String position;

    private String email;

    private String number;

    private String telegram;

    private Long completedTask;

    private Long failedTasks;

    private Timestamp startTime;

    private Timestamp finishTime;

    private Role role;

    private List<Task> taskIds;

    private Boolean primaryOnboarding;

    public static UserDTO toModel(User entity) {
        UserDTO model = new UserDTO();
        model.setName(entity.getName());
        model.setSurname(entity.getSurname());
        model.setPatronymic(entity.getPatronymic());
        model.setPosition(entity.getPosition());
        model.setEmail(entity.getEmail());
        model.setNumber(entity.getNumber());
        model.setTelegram(entity.getTelegram());
        model.setCompletedTask(entity.getCompletedTasks());
        model.setFailedTasks(entity.getFailedTasks());
        model.setStartTime(entity.getStartTime());
        model.setFinishTime(entity.getFinishTime());
        model.setRole(entity.getRole());
//        model.setTaskIds(entity.getTaskIds());
        model.setPrimaryOnboarding(entity.getPrimaryOnboarding());
        return model;
    }
}
