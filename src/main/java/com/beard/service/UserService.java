package com.beard.service;

import com.beard.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    boolean add(User user);

    boolean update(User user);

    Optional<User> findById(Long id);

    List<User> findAll();

    boolean deleteById(Long id);

    Optional<User> findByEmail(String email);

    List<User> findAllMasters();

    List<User> findUsersForPagination(int startRecord, int recordsPerPage);

    int getNumberOfRows();
}
