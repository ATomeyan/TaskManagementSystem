package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

import static com.processing.taskmanagementsystem.utils.DBConstants.CRATED;
import static com.processing.taskmanagementsystem.utils.DBConstants.UPDATED;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "VARCHAR(36)")
    private String uuid;

    @Column(name = CRATED, nullable = false)
    @CreatedDate
    private LocalDateTime created;

    @Column(name = UPDATED)
    @LastModifiedDate
    private LocalDateTime updated;
}