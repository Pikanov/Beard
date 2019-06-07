package com.beard.repository.impl;

import com.beard.entity.Role;
import com.beard.entity.User;
import com.beard.repository.UserRepository;
import com.beard.util.ConnectorDB;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final static Logger LOGGER = Logger.getLogger(UserRepositoryImpl.class);

    private ConnectorDB connectorDB;

    private static final String INSERT = "INSERT INTO users (first_name, last_name,email," +
                                         "password, phone, role_id)" +
                                         "VALUES (?, ?, ?, ?, ?, ?)";

    private static final String UPDATE = "UPDATE users SET first_name=?,last_name=?, email=?, " +
                                         "password=?, phone=?, role_id=? " +
                                         "WHERE user_id=?";

    private static final String FIND_BY_ID = "SELECT * FROM users " +
                                             "JOIN roles ON users.role_id = roles.role_id " +
                                             "WHERE user_id = ?";

    private static final String DELETE_BY_ID = "DELETE FROM users " +
                                               "WHERE user_id = ?";

    private static final String FIND_BY_EMAIL = "SELECT * FROM users " +
                                                "JOIN roles ON users.role_id = roles.role_id " +
                                                "WHERE email = ?";

    private static final String FIND_ALL_MASTERS = "SELECT * FROM users " +
                                                   "JOIN roles ON users.role_id = roles.role_id " +
                                                   "WHERE users.role_id = 3";

    private static final String FIND_ALL = "SELECT * FROM users " +
                                           "JOIN roles " +
                                           "ON users.role_id = roles.role_id";

    private static final String FIND_FOR_PAGINATION = "SELECT * FROM users " +
                                                      "JOIN roles ON users.role_id = roles.role_id " +
                                                      "LIMIT ?, ?";

    private static final String GET_NUMBER_OF_ROWS =  "SELECT COUNT(user_id) FROM users";


    public UserRepositoryImpl() {
        this.connectorDB = new ConnectorDB();
    }

    @Override
    public boolean add(User user) {
        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setLong(6, user.getRole().getRoleId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query add user");
            return false;
        }
        return true;
    }

    @Override
    public boolean update(User user) {

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(UPDATE);
            ps.setString(1, user.getFirstName());
            ps.setString(2, user.getLastName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setLong(6, user.getRole().getRoleId());
            ps.setLong(7, user.getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query update user");
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> findById(Long userId) {

        try (Connection connection = connectorDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return userBuilder(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query update user");
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteById(Long userId) {

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(DELETE_BY_ID);
            ps.setLong(1, userId);
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query deleteById user");
            return false;
        }
        return true;
    }

    @Override
    public Optional<User> findByEmail(String email) {

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps =connection.prepareStatement(FIND_BY_EMAIL);
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return userBuilder(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findByEmail user");
        }
        return Optional.empty();
    }

    @Override
    public List<User> findAllMasters() {

        return getUsers(FIND_ALL_MASTERS);
    }

    @Override
    public List<User> findUsersForPagination(int startRecord, int recordsPerPage) {

        List<User> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_FOR_PAGINATION);
            ps.setInt(1, startRecord);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(userBuilder(rs).get());
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findUsersForPagination user");
        }
        return result;
    }

    @Override
    public int getNumberOfRows() {

        int numberOfRows = 0;

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(GET_NUMBER_OF_ROWS);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                numberOfRows = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query getNumberOfRows");
        }
        return numberOfRows;
    }

    @Override
    public List<User> findAll() {
        return getUsers(FIND_ALL);
    }

    private List<User> getUsers(String sqlQuery) {
        List<User> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(sqlQuery);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(userBuilder(rs).get());
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query getUsers");
        }
        return result;
    }

    private Optional<User> userBuilder(ResultSet resultSet) throws SQLException {

        return Optional.ofNullable(User.builder()
                .withUserId(resultSet.getLong("user_id"))
                .withFirstName(resultSet.getString("first_name"))
                .withLastName(resultSet.getString("last_name"))
                .withEmail(resultSet.getString("email"))
                .withPassword(resultSet.getString("password"))
                .withPhoneNumber(resultSet.getString("phone"))
                .withRole(new Role(resultSet.getLong("role_id"),
                        resultSet.getString("role")))
                .build());
    }
}
