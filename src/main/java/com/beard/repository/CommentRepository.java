package com.beard.repository;

import com.beard.entity.Comment;

import java.util.List;

public interface CommentRepository {

    List<Comment> findAll();

    Comment findById(Long id);

    boolean deleteById(Long id);

    boolean add(Comment comment);

    boolean update(Comment comment);

    List<Comment> findCommentsForPagination(int startRecord, int recordsPerPage);

    int getNumberOfRows();
}
