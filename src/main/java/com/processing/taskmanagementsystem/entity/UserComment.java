package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TB_USER_COMMENT)
public class UserComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "varchar(36)")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = USER_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = COMMENT_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        UserComment that = (UserComment) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(user, that.user)
                .append(comment, that.comment)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(user)
                .append(comment)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("user", user)
                .append("comment", comment)
                .toString();
    }
}