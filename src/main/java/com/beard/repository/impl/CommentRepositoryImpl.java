package com.beard.repository.impl;

import com.beard.entity.Comment;
import com.beard.entity.User;
import com.beard.repository.CommentRepository;
import com.beard.util.ConnectorDB;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CommentRepositoryImpl implements CommentRepository {
    private final static Logger LOGGER = Logger.getLogger(CommentRepositoryImpl.class);

    private ConnectorDB connectorDB;

    private static final String FIND_ALL = "SELECT * FROM comments " +
            "JOIN users ON comments.user_id = users.user_id";

    private static final String FIND_BY_ID = "SELECT * FROM comments " +
            "JOIN users ON comments.user_id = users.user_id " +
            "WHERE comment_id = ?";

    private static final String INSERT = "INSERT INTO comments (comment, user_id) " +
            "VALUES (?, ?)";

    private static final String FIND_FOR_PAGINATION = "SELECT * FROM comments " +
            "JOIN users ON comments.user_id = users.user_id " +
            "LIMIT ?, ?";

    private static final String GET_NUMBER_OF_ROWS = "SELECT COUNT(comment_id) FROM comments";

    public CommentRepositoryImpl() {
        this.connectorDB = new ConnectorDB();
    }

    @Override
    public List<Comment> findAll() {
        List<Comment> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_ALL);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(commentBuilder(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findAll");
            throw new RuntimeException(e);
        }
        return result;
    }

    @Override
    public Comment findById(Long id) {
        try (Connection connection = connectorDB.getDataSource().getConnection();
             PreparedStatement ps = connection.prepareStatement(FIND_BY_ID)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return commentBuilder(rs);
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findById");
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean deleteById(Long id) {
        throw new RuntimeException();
    }

    @Override
    public boolean add(Comment comment) {
        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(INSERT);
            ps.setString(1, comment.getComment());
            ps.setLong(2, comment.getUser().getUserId());
            ps.executeUpdate();
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query add");
            throw new RuntimeException(e);
        }
        return true;
    }

    @Override
    public boolean update(Comment comment) {
        throw new RuntimeException();
    }

    @Override
    public List<Comment> findCommentsForPagination(int startRecord, int recordsPerPage) {

        List<Comment> result = new ArrayList<>();

        try (Connection connection = connectorDB.getDataSource().getConnection()) {
            PreparedStatement ps = connection.prepareStatement(FIND_FOR_PAGINATION);
            ps.setInt(1, startRecord);
            ps.setInt(2, recordsPerPage);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                result.add(commentBuilder(rs));
            }
        } catch (SQLException e) {
            LOGGER.warn("incorrect sql query findCommentsForPagination");
            throw new RuntimeException(e);
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
            throw new RuntimeException(e);
        }
        return numberOfRows;
    }

    private Comment commentBuilder(ResultSet rs) throws SQLException {
        return Comment.builder()
                .withCommentId(rs.getLong("comment_id"))
                .withComment(rs.getString("comment"))
                .withUser(User.builder()
                        .withUserId(rs.getLong("user_id"))
                        .withEmail(rs.getString("email"))
                        .withFirstName(rs.getString("first_name"))
                        .withLastName(rs.getString("last_name"))
                        .build()
                )
                .build();
    }
}
