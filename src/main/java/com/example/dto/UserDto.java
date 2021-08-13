package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
public class UserDto {
    Long id;
    String name;
    String email;
    String avatar;
    Timestamp createdAt;
    Timestamp updateAt;
}
