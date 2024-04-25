package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.List;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = TB_COMMENT)
public class Comment extends BaseEntity {

    @Column(name = CONTENT, nullable = false)
    private String content;

    @OneToMany(mappedBy = COMMENT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<TaskComment> taskComments;

    @OneToMany(mappedBy = COMMENT, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<UserComment> userComments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        return new EqualsBuilder()
                .append(content, comment.content)
                .append(taskComments, comment.taskComments)
                .append(userComments, comment.userComments)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(content)
                .append(taskComments)
                .append(userComments)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("content", content)
                .append("taskComments", taskComments)
                .append("taskUsers", userComments)
                .toString();
    }
}