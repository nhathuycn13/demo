package com.example.service;

import com.example.entity.User;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T> {
    Iterable<T> getAll();
    Optional<T> find(Long id);
    T save(T t);
    T update(Long id, T t);
    void destroy(Long id);

}
