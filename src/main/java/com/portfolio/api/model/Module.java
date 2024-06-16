package com.portfolio.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int semester;
    private int creditPoints;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "university_id")
    private University university;


    // Constructors

    public Module(Long id, String name, int semester, int creditPoints, University university) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.creditPoints = creditPoints;
        this.university = university;
    }

    public Module() {
    }

    // Getters and Setters

    @JsonProperty("id")
    public Long getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Long id) {
        this.id = id;
    }

    @JsonProperty("name")
    public String getName() {
        return name;
    }

    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    @JsonProperty("semester")
    public int getSemester() {
        return semester;
    }

    @JsonProperty("semester")
    public void setSemester(int semester) {
        this.semester = semester;
    }

    @JsonProperty("creditPoints")
    public int getCreditPoints() {
        return creditPoints;
    }

    @JsonProperty("creditPoints")
    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    @JsonProperty("university")
    public University getUniversity() {
        return university;
    }

    @JsonProperty("university")
    public void setUniversity(University university) {
        this.university = university;
    }
}
