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
@Table(name = TB_TASK_COMMENT)
public class TaskComment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "varchar(36)")
    private String uuid;

    @ManyToOne
    @JoinColumn(name = TASK_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private Task task;

    @ManyToOne
    @JoinColumn(name = COMMENT_UID, referencedColumnName = UUID)
    @ToString.Exclude
    private Comment comment;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TaskComment that = (TaskComment) o;

        return new EqualsBuilder()
                .append(uuid, that.uuid)
                .append(task, that.task)
                .append(comment, that.comment)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(uuid)
                .append(task)
                .append(comment)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("uuid", uuid)
                .append("task", task)
                .append("comment", comment)
                .toString();
    }
}