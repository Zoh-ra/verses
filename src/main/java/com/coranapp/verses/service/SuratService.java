package com.coranapp.verses.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SuratService {

    private final String QURAN_API_BASE_URL = "http://api.alquran.cloud/v1";

    @Autowired
    private RestTemplate restTemplate;

    public Object getAllSurats(){
        String url = QURAN_API_BASE_URL + "/surah";
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getSuratById(int suratNumber) {
        String url = QURAN_API_BASE_URL + "/surah/" + suratNumber;
        return restTemplate.getForObject(url, Object.class);
    }

    public Object getVersetsBySurat(int suratNumber) {
        String url = QURAN_API_BASE_URL + "/surah/" + suratNumber + "/editions/quran-uthmani,fr.hamidullah";
        return restTemplate.getForObject(url, Object.class);
    }
}