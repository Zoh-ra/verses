package com.coranapp.verses.controller;

import com.coranapp.verses.dto.PanierDTO;
import com.coranapp.verses.dto.VersetsDTO;
import com.coranapp.verses.entity.Panier;
import com.coranapp.verses.entity.VersetsParPanier;
import com.coranapp.verses.service.PanierService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/paniers")
@Tag(name = "Panier Controller", description = "API pour gérer les paniers de versets")
public class PanierController {

    @Autowired
    private PanierService panierService;

    @Operation(summary = "Créer un nouveau panier",
            description = "Crée un nouveau panier à partir des informations fournies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Panier créé avec succès",
                    content = @Content(schema = @Schema(implementation = Panier.class))),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PostMapping
    public ResponseEntity<Panier> creerPanier(
            @Parameter(description = "Informations du panier à créer", required = true)
            @RequestBody PanierDTO panierDTO) {
        return ResponseEntity.ok(panierService.creerPanier(panierDTO));
    }

    @Operation(summary = "Ajouter un verset à un panier",
            description = "Ajoute un verset spécifique à un panier existant")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verset ajouté avec succès"),
            @ApiResponse(responseCode = "404", description = "Panier non trouvé"),
            @ApiResponse(responseCode = "400", description = "Données invalides"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @PostMapping("/{panierId}/versets")
    public ResponseEntity<Void> ajouterVerset(
            @Parameter(description = "ID du panier", required = true)
            @PathVariable Integer panierId,
            @Parameter(description = "Informations du verset à ajouter", required = true)
            @RequestBody VersetsDTO versetDTO) {
        panierService.ajouterVerset(panierId, versetDTO);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Récupérer tous les paniers",
            description = "Retourne la liste de tous les paniers existants")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des paniers récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping
    public ResponseEntity<List<Panier>> getAllPaniers() {
        return ResponseEntity.ok(panierService.getAllPaniers());
    }

    @Operation(summary = "Récupérer un panier par son ID",
            description = "Retourne les détails d'un panier spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Panier trouvé"),
            @ApiResponse(responseCode = "404", description = "Panier non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{panierId}")
    public ResponseEntity<Panier> getPanierById(
            @Parameter(description = "ID du panier à récupérer", required = true)
            @PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getPanierById(panierId));
    }

    @Operation(summary = "Récupérer les versets d'un panier",
            description = "Retourne la liste de tous les versets contenus dans un panier spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des versets récupérée avec succès"),
            @ApiResponse(responseCode = "404", description = "Panier non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{panierId}/versets")
    public ResponseEntity<List<VersetsParPanier>> getVersetsPanier(
            @Parameter(description = "ID du panier", required = true)
            @PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getVersetsPanier(panierId));
    }

    @Operation(summary = "Récupérer un verset aléatoire d'un panier",
            description = "Retourne un verset choisi au hasard parmi ceux contenus dans le panier spécifié")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Verset aléatoire récupéré avec succès"),
            @ApiResponse(responseCode = "404", description = "Panier non trouvé ou vide"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{panierId}/versets/random")
    public ResponseEntity<VersetsParPanier> getVersetAleatoire(
            @Parameter(description = "ID du panier", required = true)
            @PathVariable Integer panierId) {
        return ResponseEntity.ok(panierService.getVersetAleatoire(panierId));
    }

    @Operation(summary = "Supprimer un panier",
            description = "Supprime un panier et tous ses versets associés")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Panier supprimé avec succès"),
            @ApiResponse(responseCode = "404", description = "Panier non trouvé"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @DeleteMapping("/{panierId}")
    public ResponseEntity<Void> supprimerPanier(
            @Parameter(description = "ID du panier à supprimer", required = true)
            @PathVariable Integer panierId) {
        panierService.supprimerPanier(panierId);
        return ResponseEntity.ok().build();
    }
}