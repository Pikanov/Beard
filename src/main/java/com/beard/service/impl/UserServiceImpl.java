package com.beard.service.impl;

import com.beard.entity.User;
import com.beard.repository.UserRepository;
import com.beard.service.UserService;

import java.util.List;
import java.util.Optional;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean add(User user) {
        return userRepository.add(user);
    }

    @Override
    public boolean update(User user) {
        return userRepository.update(user);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteById(Long id) {
        return userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findAllMasters() {
        return userRepository.findAllMasters();
    }

    @Override
    public List<User> findUsersForPagination(int startRecord, int recordsPerPage) {
        return userRepository.findUsersForPagination(startRecord, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() {
        return userRepository.getNumberOfRows();
    }
}
