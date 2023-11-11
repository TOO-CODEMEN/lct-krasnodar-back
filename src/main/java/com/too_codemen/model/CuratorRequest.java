package com.too_codemen.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.too_codemen.entity.Role;
import com.too_codemen.entity.User;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Data
public class CuratorRequest {

    private String name;

    private String surname;

    private String patronymic;

    private String password;

    private String position;

    private String email;

    private String number;

    private String telegram;

    private Role role;

}
