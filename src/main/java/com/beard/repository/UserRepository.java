package com.beard.repository;


import com.beard.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {

    boolean add(User user);

    boolean update(User user);

    Optional<User> findById(Long userId);

    List<User> findAll();

    boolean deleteById(Long userId);

    Optional<User> findByEmail(String email);

    List<User> findAllMasters();

    List<User> findUsersForPagination(int startRecord, int recordsPerPage);

    int getNumberOfRows();
}
