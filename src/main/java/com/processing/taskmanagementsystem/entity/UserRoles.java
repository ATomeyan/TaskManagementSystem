package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TB_USER_ROLES)
public class UserRoles {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = USER_UID, referencedColumnName = UUID)
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = ROLE_UID, referencedColumnName = UUID)
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