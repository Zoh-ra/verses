package com.coranapp.verses.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "paniers")
@Data
public class Panier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nom;

    @OneToMany(mappedBy = "panier", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<VersetsParPanier> versets = new ArrayList<>();

    public void addVerset(VersetsParPanier verset) {
        versets.add(verset);
        verset.setPanier(this);
    }
}
