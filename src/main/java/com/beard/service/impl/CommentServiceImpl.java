package com.beard.service.impl;

import com.beard.entity.Comment;
import com.beard.repository.CommentRepository;
import com.beard.service.CommentService;

import java.util.List;

public class CommentServiceImpl implements CommentService {

    private CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public List<Comment> findAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment findById(Long id) {
        return commentRepository.findById(id);
    }

    @Override
    public boolean deleteById(Long id) {
        return commentRepository.deleteById(id);
    }

    @Override
    public boolean add(Comment comment) {
        return commentRepository.add(comment);
    }

    @Override
    public boolean update(Comment comment) {
        return commentRepository.update(comment);
    }

    @Override
    public List<Comment> findCommentsForPagination(int startRecord, int recordsPerPage) {
        return commentRepository.findCommentsForPagination(startRecord, recordsPerPage);
    }

    @Override
    public int getNumberOfRows() {
        return commentRepository.getNumberOfRows();
    }
}
