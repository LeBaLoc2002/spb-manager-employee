package com.example.managerEmployees.service;

import java.util.List;
import java.util.Optional;

public interface IGeneralService <T> {

    List<T> findAll();

    T getById(Long id);

    Optional<T> findById(Long id);

    T getById(String id);

    Optional<T> findById(String id);

    T save(T t);

    void remove(Long id);
}