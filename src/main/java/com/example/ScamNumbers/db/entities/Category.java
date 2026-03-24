package com.example.ScamNumbers.db.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NonNull
    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<Report> reports;

    public Category(String name) {
        this.name = name;
    }
}
