package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_user_roles")
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_uid", referencedColumnName = "uuid")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_uid", referencedColumnName = "uuid")
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserRoles userRoles = (UserRoles) o;

        return new EqualsBuilder()
                .append(uuid, userRoles.uuid)
                .append(user, userRoles.user)
                .append(role, userRoles.role)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(user)
                .append(role)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("user", user)
                .append("role", role)
                .toString();
    }
}