package com.example.ScamNumbers.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "search_log")
@EntityListeners(AuditingEntityListener.class)
public class SearchLog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @NonNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "number_id", nullable = false, updatable = false)
    private Number number;
    @NonNull
    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant searchedAt;
}
