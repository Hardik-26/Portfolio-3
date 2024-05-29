package com.portfolio.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;


@Entity
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int semester;
    private int creditPoints;

    @ManyToOne
    @JoinColumn(name = "partner_university_id")
    private PartnerUniversity partnerUniversity;

    // Constructors
    public Module(Long id, String name, int semester, int creditPoints, PartnerUniversity partnerUniversity) {
        this.id = id;
        this.name = name;
        this.semester = semester;
        this.creditPoints = creditPoints;
        this.partnerUniversity = partnerUniversity;
    }

    public Module() {}

    // Getter & Setter


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

    public PartnerUniversity getPartnerUniversity() {
        return partnerUniversity;
    }

    public void setPartnerUniversity(PartnerUniversity partnerUniversity) {
        this.partnerUniversity = partnerUniversity;
    }
}