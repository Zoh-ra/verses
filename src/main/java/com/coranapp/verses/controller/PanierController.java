package com.coranapp.verses.controller;

import com.coranapp.verses.dto.PanierDTO;
import com.coranapp.verses.dto.VersetsDTO;
import com.coranapp.verses.entity.Panier;
import com.coranapp.verses.entity.VersetsParPanier;
import com.coranapp.verses.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @PostMapping
    public ResponseEntity<Panier> creerPanier(@RequestBody PanierDTO panierDTO) {
        return ResponseEntity.ok(panierService.creerPanier(panierDTO));
    }

    @PostMapping("/{panierId}/versets")
    public ResponseEntity<Void> ajouterVerset(
            @PathVariable Integer panierId,
            @RequestBody VersetsDTO versetDTO) {
        panierService.ajouterVerset(panierId, versetDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Panier>> getAllPaniers() {
        return ResponseEntity.ok(panierService.getAllPaniers());
    }

    @GetMapping("/{panierId}")
    public ResponseEntity<Panier> getPanierById(@PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getPanierById(panierId));
    }

    @GetMapping("/{panierId}/versets")
    public ResponseEntity<List<VersetsParPanier>> getVersetsPanier(@PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getVersetsPanier(panierId));
    }

    @GetMapping("/{panierId}/versets/random")
    public ResponseEntity<VersetsParPanier> getVersetAleatoire(@PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getVersetAleatoire(panierId));
    }

    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> supprimerPanier(@PathVariable Integer panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.ok().build();
    }
}
