package com.coranapp.verses.dto;

import lombok.Data;
import java.util.List;

@Data
public class PanierResponseDTO {
    private Integer id;
    private String nom;
    private List<VersetsDTO> versets;
}
