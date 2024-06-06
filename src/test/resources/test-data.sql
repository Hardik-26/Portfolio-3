-- Insert sample data for universities
INSERT INTO university (id, name, country, department, url, contact_person, students_sent, students_accepted, spring_semester_start, autumn_semester_start)
VALUES (1, 'Christ University', 'India', 'Department of Information Technology', 'http://example.com', 'Dr. Ravi Kumar', 10, 10, '2025-01-25', '2024-09-01');

-- Insert sample data for modules
INSERT INTO module (id, name, semester, credit_points, university_id)
VALUES (1, 'Introduction to Quantum Computing', 1, 5, 1);
