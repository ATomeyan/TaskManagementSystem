package com.processing.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;
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

    @Column(name = CRATED, nullable = false, updatable = false)
    @CreationTimestamp(source = SourceType.DB)
    private LocalDateTime created;

    @Column(name = UPDATED)
    @UpdateTimestamp
    private LocalDateTime updated;
}