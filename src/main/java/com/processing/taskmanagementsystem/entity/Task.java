package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.time.LocalDate;
import java.util.List;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TB_TASK)
public class Task extends BaseEntity {

    @Column(name = TITLE, nullable = false)
    private String title;

    @Column(name = DESCRIPTION, nullable = false)
    private String description;

    @Column(name = DUE_DATE, nullable = false)
    private LocalDate dueDate;

    @Column(name = PRIORITY, nullable = false)
    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Column(name = STATUS, nullable = false)
    @Enumerated(EnumType.STRING)
    private Status status;

    @OneToMany(mappedBy = TASK, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<TaskUser> taskUsers;

    @OneToMany(mappedBy = TASK, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskComment> taskComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        return new EqualsBuilder()
                .append(title, task.title)
                .append(description, task.description)
                .append(dueDate, task.dueDate)
                .append(priority, task.priority)
                .append(status, task.status)
                .append(taskUsers, task.taskUsers)
                .append(taskComments, task.taskComments)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(title)
                .append(description)
                .append(dueDate)
                .append(priority)
                .append(status)
                .append(taskUsers)
                .append(taskComments)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("title", title)
                .append("description", description)
                .append("dueDate", dueDate)
                .append("priority", priority)
                .append("status", status)
                .append("taskUser", taskUsers)
                .append("taskComments", taskComments)
                .toString();
    }
}