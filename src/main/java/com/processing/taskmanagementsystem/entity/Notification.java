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

import java.time.LocalDateTime;
import java.util.List;

import static com.processing.taskmanagementsystem.utils.DBConstants.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = TB_NOTIFICATION)
public class Notification extends BaseEntity {

    @Column(name = DATETIME, nullable = false)
    private LocalDateTime dateTime;

    @Column(name = MESSAGE, nullable = false)
    private String message;

    @Column(name = IS_READ, nullable = false)
    private Boolean isRead;

    @OneToMany(mappedBy = NOTIFICATION, fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(FetchMode.JOIN)
    private List<UserNotification> user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Notification that = (Notification) o;

        return new EqualsBuilder()
                .append(dateTime, that.dateTime)
                .append(message, that.message)
                .append(isRead, that.isRead)
                .append(user, that.user)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(dateTime)
                .append(message)
                .append(isRead)
                .append(user)
                .toHashCode();
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("dateTime", dateTime)
                .append("message", message)
                .append("isRead", isRead)
                .append("user", user)
                .toString();
    }
}