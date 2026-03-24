package com.example.ScamNumbers.db.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@RequiredArgsConstructor
@EntityListeners(AuditingEntityListener.class)
@Table(
        name = "reports",
        uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "number_id"}))
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", nullable = false, updatable = false)
    @JsonBackReference
    private User user;
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "number_id", nullable = false, updatable = false)
    @JsonBackReference
    private Number number;
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "category_id", nullable = false, updatable = false)
    @JsonBackReference
    private Category category;
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;
}
