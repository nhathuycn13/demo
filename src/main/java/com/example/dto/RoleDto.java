package com.example.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Getter
//@Setter
public class RoleDto {
    private Long id;
    private String name;


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}
