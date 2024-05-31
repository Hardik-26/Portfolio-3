package com.portfolio.api;

import com.portfolio.api.model.Module;
import com.portfolio.api.model.University;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import com.portfolio.api.frontend.Frontend;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ApiApplicationIT {

    @Autowired
    private Frontend frontend;

    // University tests
    @Test
    @Sql("/test-data.sql")  // Load some test data
    public void testCreateUniversity() {
        University university = new University();
        university.setName("Test University");
        university.setCountry("Test Country");
        university.setDepartment("Test Department");
        university.setUrl("http://testuniversity.com");
        university.setContactPerson("Test Person");
        university.setStudentsSent(10);
        university.setStudentsAccepted(10);
        university.setSpringSemesterStart(LocalDate.of(2025, 1, 25));
        university.setAutumnSemesterStart(LocalDate.of(2024, 9, 1));

        University createdUniversity = frontend.createUniversity(university);
        assertNotNull(createdUniversity.getId());
        assertEquals("Test University", createdUniversity.getName());
    }

    @Test
    public void testGetUniversity() {
        University university = frontend.getUniversity(1L);
        assertNotNull(university);
        assertEquals("Christ University", university.getName());
    }

    @Test
    public void testUpdateUniversity() {
        University university = frontend.getUniversity(1L);
        university.setName("Updated University");

        frontend.updateUniversity(1L, university);
        University updatedUniversity = frontend.getUniversity(1L);
        assertEquals("Updated University", updatedUniversity.getName());
    }

    @Test
    public void testDeleteUniversity() {
        frontend.deleteUniversity(1L);
        University university = frontend.getUniversity(1L);
        assertNull(university);
    }

    @Test
    public void testSearchUniversities() {
        String query = "name=Test";
        University[] universities = frontend.searchUniversities(query);
        assertTrue(universities.length > 0);
    }

    // Module tests
    @Test
    public void testCreateModule() {
        Module module = new Module();
        module.setName("Introduction to Quantum Computing");
        module.setSemester(1);
        module.setCreditPoints(5);

        University university = frontend.getUniversity(1L);
        module.setUniversity(university);

        Module createdModule = frontend.createModule(module);
        assertNotNull(createdModule.getId());
        assertEquals("Introduction to Quantum Computing", createdModule.getName());
    }

    @Test
    public void testGetModule() {
        Module module = frontend.getModule(1L);
        assertNotNull(module);
        assertEquals("Introduction to Quantum Computing", module.getName());
    }

    @Test
    public void testUpdateModule() {
        Module module = frontend.getModule(1L);
        module.setName("Advanced Quantum Computing");

        frontend.updateModule(1L, module);
        Module updatedModule = frontend.getModule(1L);
        assertEquals("Advanced Quantum Computing", updatedModule.getName());
    }

    @Test
    public void testDeleteModule() {
        frontend.deleteModule(1L);
        Module module = frontend.getModule(1L);
        assertNull(module);
    }
}