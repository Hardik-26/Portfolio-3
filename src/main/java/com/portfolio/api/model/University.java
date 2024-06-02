package com.portfolio.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class University {
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getStudentsSent() {
        return studentsSent;
    }

    public void setStudentsSent(int studentsSent) {
        this.studentsSent = studentsSent;
    }

    public int getStudentsAccepted() {
        return studentsAccepted;
    }

    public void setStudentsAccepted(int studentsAccepted) {
        this.studentsAccepted = studentsAccepted;
    }

    public LocalDate getSpringSemesterStart() {
        return springSemesterStart;
    }

    public void setSpringSemesterStart(LocalDate springSemesterStart) {
        this.springSemesterStart = springSemesterStart;
    }

    public LocalDate getAutumnSemesterStart() {
        return autumnSemesterStart;
    }

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
