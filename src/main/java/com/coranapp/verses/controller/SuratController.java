package com.coranapp.verses.controller;

import com.coranapp.verses.service.SuratService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surats")
@Tag(name = "Surat Controller", description = "API pour gérer les sourates du Coran")
public class SuratController {

    @Autowired
    private SuratService suratService;

    @Operation(summary = "Récupérer toutes les sourates",
            description = "Retourne la liste complète des sourates du Coran")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Liste des sourates récupérée avec succès"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping
    public ResponseEntity<Object> getAllSurats() {
        return ResponseEntity.ok(suratService.getAllSurats());
    }

    @Operation(summary = "Récupérer une sourate par son numéro",
            description = "Retourne une sourate spécifique en fonction de son numéro")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sourate trouvée"),
            @ApiResponse(responseCode = "404", description = "Sourate non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{suratNumber}")
    public ResponseEntity<Object> getSuratById(
            @Parameter(description = "Numéro de la sourate", required = true)
            @PathVariable int suratNumber) {
        return ResponseEntity.ok(suratService.getSuratById(suratNumber));
    }

    @Operation(summary = "Récupérer les versets d'une sourate",
            description = "Retourne tous les versets d'une sourate spécifique")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Versets trouvés"),
            @ApiResponse(responseCode = "404", description = "Sourate non trouvée"),
            @ApiResponse(responseCode = "500", description = "Erreur serveur interne")
    })
    @GetMapping("/{suratNumber}/versets")
    public ResponseEntity<Object> getVersetsBySurat(
            @Parameter(description = "Numéro de la sourate", required = true)
            @PathVariable int suratNumber) {
        return ResponseEntity.ok(suratService.getVersetsBySurat(suratNumber));
    }
}