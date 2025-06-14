package com.project.ecommerce_app.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SearchHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer search_id;

    private String searchQuery;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;
}
