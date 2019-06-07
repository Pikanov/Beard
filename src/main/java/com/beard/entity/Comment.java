package com.beard.entity;

import java.util.Objects;

public class Comment {
    private Long commentId;
    private String comment;
    private User user;

    public Comment(Builder builder) {
        this.commentId = builder.commentId;
        this.comment = builder.comment;
        this.user = builder.user;
    }

    public Comment() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getCommentId() {
        return commentId;
    }

    public String getComment() {
        return comment;
    }

    public User getUser() {
        return user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Comment comment1 = (Comment) o;
        return Objects.equals(commentId, comment1.commentId) &&
                Objects.equals(comment, comment1.comment) &&
                Objects.equals(user, comment1.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId, comment, user);
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", comment='" + comment + '\'' +
                ", user=" + user +
                '}';
    }

    public static class Builder {
        private Long commentId;
        private String comment;
        private User user;

        private Builder() {
        }

        public Comment build() {
            return new Comment(this);
        }

        public Builder withCommentId(Long commentId) {
            this.commentId = commentId;
            return this;
        }

        public Builder withComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder withUser(User user) {
            this.user = user;
            return this;
        }
    }
}
