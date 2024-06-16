package com.portfolio.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.hateoas.RepresentationModel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class University extends RepresentationModel<University> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String country;
    private String department;
    private String url;
    private String contactPerson;
    private int studentsSent;
    private int studentsAccepted;
    private LocalDate springSemesterStart;
    private LocalDate autumnSemesterStart;
    @OneToMany(mappedBy = "university", cascade = CascadeType.ALL)
    private List<Module> modules;

    // Constructors-
    public University(Long id, String name, String country, String department, String url, String contactPerson, int studentsSent, int studentsAccepted, LocalDate springSemesterStart, LocalDate autumnSemesterStart, List<Module> modules) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.department = department;
        this.url = url;
        this.contactPerson = contactPerson;
        this.studentsSent = studentsSent;
        this.studentsAccepted = studentsAccepted;
        this.springSemesterStart = springSemesterStart;
        this.autumnSemesterStart = autumnSemesterStart;
        this.modules = modules;
    }

    public University() {
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

    @JsonProperty("country")
    public String getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(String country) {
        this.country = country;
    }

    @JsonProperty("department")
    public String getDepartment() {
        return department;
    }

    @JsonProperty("department")
    public void setDepartment(String department) {
        this.department = department;
    }

    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    @JsonProperty("contactPerson")
    public String getContactPerson() {
        return contactPerson;
    }

    @JsonProperty("contactPerson")
    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    @JsonProperty("studentsSent")
    public int getStudentsSent() {
        return studentsSent;
    }

    @JsonProperty("studentsSent")
    public void setStudentsSent(int studentsSent) {
        this.studentsSent = studentsSent;
    }

    @JsonProperty("studentsAccepted")
    public int getStudentsAccepted() {
        return studentsAccepted;
    }

    @JsonProperty("studentsAccepted")
    public void setStudentsAccepted(int studentsAccepted) {
        this.studentsAccepted = studentsAccepted;
    }

    @JsonProperty("springSemesterStart")
    public LocalDate getSpringSemesterStart() {
        return springSemesterStart;
    }

    @JsonProperty("springSemesterStart")
    public void setSpringSemesterStart(LocalDate springSemesterStart) {
        this.springSemesterStart = springSemesterStart;
    }

    @JsonProperty("autumnSemesterStart")
    public LocalDate getAutumnSemesterStart() {
        return autumnSemesterStart;
    }

    @JsonProperty("autumnSemesterStart")
    public void setAutumnSemesterStart(LocalDate autumnSemesterStart) {
        this.autumnSemesterStart = autumnSemesterStart;
    }

    public List<Module> getModules() {
        return modules;
    }

    public void setModules(List<Module> modules) {
        this.modules = modules;
    }
}
