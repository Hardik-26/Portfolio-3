package com.portfolio.api.model;

import jakarta.persistence.*;

@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int semester;
    private int creditPoints;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public University getUniversity() {
        return university;
    }

    public void setUniversity(University university) {
        this.university = university;
    }
}
