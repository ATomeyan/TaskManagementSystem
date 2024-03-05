package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TB_USER)
public class User extends BaseEntity {

    @Column(name = FIRST_NAME, nullable = false)
    private String firstName;

    @Column(name = LAST_NAME, nullable = false)
    private String lastName;

    @Column(name = USERNAME, nullable = false, unique = true)
    private String username;

    @Column(name = PASSWORD, nullable = false)
    private String password;

    @Column(name = ENABLED, nullable = false)
    private boolean enabled;

    @OneToMany(mappedBy = USER, fetch = FetchType.LAZY)
    private List<TaskUser> taskUsers;

    @OneToMany(mappedBy = USER, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<UserRoles> userRoles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return new EqualsBuilder()
                .append(firstName, user.firstName)
                .append(lastName, user.lastName)
                .append(username, user.username)
                .append(password, user.password)
                .append(enabled, user.enabled)
                .append(taskUsers, user.taskUsers)
                .append(userRoles, user.userRoles)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(firstName)
                .append(lastName)
                .append(username)
                .append(password)
                .append(enabled)
                .append(taskUsers)
                .append(userRoles)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("username", username)
                .append("password", password)
                .append("enabled", enabled)
                .append("taskUsers", taskUsers)
                .append("userRoles", userRoles)
                .toString();
    }
}