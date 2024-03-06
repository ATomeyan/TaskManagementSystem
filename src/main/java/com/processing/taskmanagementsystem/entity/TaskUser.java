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
@Table(name = TB_TASK_USER)
public class TaskUser {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = USER_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private User user;

    @ManyToOne
    @JoinColumn(name = TASK_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private Task task;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TaskUser taskUser = (TaskUser) o;

        return new EqualsBuilder()
                .append(uuid, taskUser.uuid)
                .append(user, taskUser.user)
                .append(task, taskUser.task)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(user)
                .append(task)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("user", user)
                .append("task", task)
                .toString();
    }
}