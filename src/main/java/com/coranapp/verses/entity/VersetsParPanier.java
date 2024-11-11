package com.coranapp.verses.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "versetsparpanier")
@Data
public class VersetsParPanier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "panierid")
    @JsonIgnore
    private Panier panier;

    @Column(nullable = false)
    private Integer suratNumber;

    @Column(nullable = false)
    private Integer versetNumber;

    @Column(columnDefinition = "TEXT")
    private String texteVerset;
}
