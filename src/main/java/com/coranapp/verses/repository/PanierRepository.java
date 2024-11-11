package com.coranapp.verses.repository;

import com.coranapp.verses.entity.Panier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PanierRepository extends JpaRepository<Panier, Integer> {
    List<Panier> findByNomContainingIgnoreCase(String nom);
}