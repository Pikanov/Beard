package com.beard.entity;

import java.util.List;
import java.util.Objects;

public class User {
    private Long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNumber;
    private Role role;
    private List<ScheduleItem> scheduleItem;

    public User(Builder builder) {
        this.userId = builder.userId;
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        this.email = builder.email;
        this.password = builder.password;
        this.phoneNumber = builder.phoneNumber;
        this.role = builder.role;
        this.scheduleItem = builder.scheduleItem;
    }

    public User() {
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getUserId() {
        return userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Role getRole() {
        return role;
    }

    public List<ScheduleItem> getScheduleItem() {
        return scheduleItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(userId, user.userId) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(email, user.email) &&
                Objects.equals(password, user.password) &&
                Objects.equals(phoneNumber, user.phoneNumber) &&
                Objects.equals(role, user.role) &&
                Objects.equals(scheduleItem, user.scheduleItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, firstName, lastName, email, password, phoneNumber,
                role, scheduleItem);
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", role=" + role +
                ", scheduleItem=" + scheduleItem +
                '}';
    }
    public static class Builder {
        private Long userId;
        private String firstName;
        private String lastName;
        private String email;
        private String password;
        private String phoneNumber;
        private Role role;
        private List<ScheduleItem> scheduleItem;

        private Builder() {
        }

        public User build() {
            return new User(this);
        }

        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        public Builder withFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public Builder withLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder withEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder withPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder withPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
            return this;
        }

        public Builder withRole(Role role) {
            this.role = role;
            return this;
        }

        public Builder withScheduleItem(List<ScheduleItem> scheduleItem) {
            this.scheduleItem = scheduleItem;
            return this;
        }
    }
}
