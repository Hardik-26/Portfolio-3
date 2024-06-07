package com.portfolio.api.frontend;

import com.portfolio.api.model.Module;
import com.portfolio.api.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.portfolio.api.config.AppConfig;
import java.util.HashMap;
import java.util.Map;

@Component
public class Frontend {

    private static final String BASE_URL = "localhost:8080/portfolio";

    @Autowired
    private RestTemplate restTemplate;

    // University methods
    public University createUniversity(University university) {
        return restTemplate.postForObject(BASE_URL + "/universities", university, University.class);
    }

    public University getUniversity(Long id) {
        return restTemplate.getForObject(BASE_URL + "/universities/{id}", University.class, id);
    }

    public void updateUniversity(Long id, University university) {
        restTemplate.put(BASE_URL + "/universities/{id}", university, id);
    }

    public void deleteUniversity(Long id) {
        restTemplate.delete(BASE_URL + "/universities/{id}", id);
    }

    public University[] searchUniversities(String query) {
        String url = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/universities")
                .query(query)
                .toUriString();
        return restTemplate.getForObject(url, University[].class);
    }

    // Module methods
    public Module createModule(Module module) {
        return restTemplate.postForObject(BASE_URL + "/modules", module, Module.class);
    }

    public Module getModule(Long id) {
        return restTemplate.getForObject(BASE_URL + "/modules/{id}", Module.class, id);
    }

    public void updateModule(Long id, Module module) {
        restTemplate.put(BASE_URL + "/modules/{id}", module, id);
    }

    public void deleteModule(Long id) {
        restTemplate.delete(BASE_URL + "/modules/{id}", id);
    }
}