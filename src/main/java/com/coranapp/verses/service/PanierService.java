package com.coranapp.verses.service;

import com.coranapp.verses.dto.PanierDTO;
import com.coranapp.verses.dto.VersetsDTO;
import com.coranapp.verses.entity.Panier;
import com.coranapp.verses.entity.VersetsParPanier;
import com.coranapp.verses.repository.PanierRepository;
import com.coranapp.verses.repository.VersetsParPanierRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {

    @Autowired
    private PanierRepository panierRepository;

    @Autowired
    private VersetsParPanierRepository versetsRepository;

    @Transactional
    public Panier creerPanier(PanierDTO panierDTO) {
        Panier panier = new Panier();
        panier.setNom(panierDTO.getNom());
        return panierRepository.save(panier);
    }

    @Transactional
    public void ajouterVerset(Integer panierId, VersetsDTO versetDTO) {
        Panier panier = panierRepository.findById(panierId)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));

        VersetsParPanier verset = new VersetsParPanier();
        verset.setPanier(panier);
        verset.setSuratNumber(versetDTO.getSuratNumber());
        verset.setVersetNumber(versetDTO.getVersetNumber());
        verset.setTexteVerset(versetDTO.getTexteVerset());

        panier.addVerset(verset);
        panierRepository.save(panier);
    }

    public List<Panier> getAllPaniers() {
        return panierRepository.findAll();
    }

    public Panier getPanierById(Integer id) {
        return panierRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Panier non trouvé"));
    }

    public VersetsParPanier getVersetAleatoire(Integer panierId) {
        return versetsRepository.findRandomVersetByPanierId(panierId);
    }

    public List<VersetsParPanier> getVersetsPanier(Integer panierId) {
        return versetsRepository.findByPanierId(panierId);
    }

    @Transactional
    public void supprimerPanier(Integer panierId) {
        panierRepository.deleteById(panierId);
    }

}
