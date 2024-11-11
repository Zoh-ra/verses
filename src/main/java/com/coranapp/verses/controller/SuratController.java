package com.coranapp.verses.controller;

import com.coranapp.verses.service.SuratService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/surats")
public class SuratController {

    @Autowired
    private SuratService suratService;

    @GetMapping
    public ResponseEntity<Object> getAllSurats() {
        return ResponseEntity.ok(suratService.getAllSurats());
    }

    @GetMapping("/{suratNumber}")
    public ResponseEntity<Object> getSuratById(@PathVariable int suratNumber) {
        return ResponseEntity.ok(suratService.getSuratById(suratNumber));
    }

    @GetMapping("/{suratNumber}/versets")
    public ResponseEntity<Object> getVersetsBySurat(@PathVariable int suratNumber) {
        return ResponseEntity.ok(suratService.getVersetsBySurat(suratNumber));
    }
}
