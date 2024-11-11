package com.coranapp.verses.repository;

import com.coranapp.verses.entity.VersetsParPanier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VersetsParPanierRepository extends JpaRepository<VersetsParPanier, Integer> {
    List<VersetsParPanier> findByPanierId(Integer panierId);

    @Query("SELECT v FROM VersetsParPanier v WHERE v.panier.id = :panierId ORDER BY RANDOM() LIMIT 1")
    VersetsParPanier findRandomVersetByPanierId(Integer panierId);
}
