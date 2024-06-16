package com.portfolio.api.frontend;

import com.portfolio.api.model.Module;
import com.portfolio.api.model.University;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class Frontend {

    private static final String BASE_URL = "http://localhost:8080/portfolio";

    @Autowired
    private RestTemplate restTemplate;

    // University methods
    public University createUniversity(University university) {
        University createdUniversity = restTemplate.postForObject(BASE_URL + "/universities", university, University.class);
        return createdUniversity;
    }

    public University getUniversity(Long id) {
        University university = restTemplate.getForObject(BASE_URL + "/universities/{id}", University.class, id);
        return university;
    }

    public void updateUniversity(Long id, University university) {
        restTemplate.put(BASE_URL + "/universities/{id}", university, id);
    }

    public void deleteUniversity(Long id) {
        restTemplate.delete(BASE_URL + "/universities/{id}", id);
    }

    // Module methods
    public Module createModule(Module module) {
        Module response = restTemplate.postForObject(BASE_URL + "/modules", module, Module.class);
        return response;
    }

    public Module getModule(Long id) {
        Module response  = restTemplate.getForObject(BASE_URL + "/modules/{id}", Module.class, id);
        return response;
    }

    public void updateModule(Long id, Module module) {
        restTemplate.put(BASE_URL + "/modules/{id}", module, id);
    }

    public void deleteModule(Long id) {
        restTemplate.delete(BASE_URL + "/modules/{id}", id);
    }

}
