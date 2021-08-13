package com.example.service;

import com.example.entity.Role;

public interface RoleService extends GeneralService<Role> {
    Role findByName(String name);
}
