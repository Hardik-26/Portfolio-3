package com.portfolio.api;

import com.portfolio.api.model.Module;
import com.portfolio.api.model.University;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import com.portfolio.api.frontend.Frontend;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class ApiApplicationIT {

    @Autowired
    private Frontend frontend;

    // University tests
    @Test
    @Order(1)
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
        assertEquals("Test University", createdUniversity.getName());
    }

    @Test
    @Order(2)
    public void testGetUniversity() {
        try {
            University university = frontend.getUniversity(1L);
            assertNotNull(university);
            assertEquals("Test University", university.getName());
            System.out.println(university);
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            System.out.println(e.getStatusCode());
            fail("Request failed: " + e.getStatusCode());
        }
    }

    // Module tests
    @Test
    @Order(3)
    public void testCreateModule() {
            Module module = new Module();
            module.setName("Introduction to Quantum Computing");
            module.setSemester(1);
            module.setCreditPoints(5);
            module.setUniversity(null);
            Module createdModule = frontend.createModule(module);
            assertNotNull(createdModule.getId());
            assertEquals("Introduction to Quantum Computing", createdModule.getName());
    }

    @Test
    @Order(4)
    public void testGetModule() {
        try {
            Module module = frontend.getModule(1L);
            assertNotNull(module);
            assertEquals("Introduction to Quantum Computing", module.getName());
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());
        }
    }

    @Test
    @Order(5)
    public void testUpdateModule() {
        try {
            Module module = frontend.getModule(1L);
            module.setName("Advanced Quantum Computing");

            frontend.updateModule(1L, module);
            Module updatedModule = frontend.getModule(1L);
            assertEquals("Advanced Quantum Computing", updatedModule.getName());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            fail("Request failed: " + e.getStatusCode());
        }
    }

    @Test
    @Order(6)
    public void testDeleteModule() {
        try {
            frontend.deleteModule(1L);
            Module module = frontend.getModule(1L);
            assertNull(module);
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());
        }
    }

    @Test
    @Order(7)
    public void testUpdateUniversity() {
        try {
            University university = frontend.getUniversity(1L);
            university.setName("Updated University");

            frontend.updateUniversity(1L, university);
            University updatedUniversity = frontend.getUniversity(1L);
            assertEquals("Updated University", updatedUniversity.getName());
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            fail("Request failed: " + e.getStatusCode());
        }
    }

    @Test
    @Order(8)
    public void testDeleteUniversity() {
        try {
            frontend.deleteUniversity(1L);
            University university = frontend.getUniversity(1L);
            assertNull(university);
        } catch (HttpClientErrorException e) {
            assertEquals(404, e.getRawStatusCode());
        }
    }

}

