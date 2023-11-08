package com.too_codemen.model;

import com.too_codemen.entity.User;
import lombok.Data;

@Data
public class AuthResult {
    String token;
    User user;
}
