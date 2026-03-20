package com.example.ScamNumbers.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "numbers")
@EntityListeners(AuditingEntityListener.class)
public class Number {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    @Column(unique = true, nullable = false)
    private String number;
    @CreatedDate
    @Column(name = "created_at", updatable = false)
    private Instant createdAt;

    @OneToMany(
            mappedBy = "number",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<Report> reports;

    @OneToMany(
            mappedBy = "number",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<SearchLog> searchLogs;
}