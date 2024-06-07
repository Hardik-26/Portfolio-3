package com.portfolio.api.specification;
import com.portfolio.api.model.University;
import org.springframework.data.jpa.domain.Specification;

public class UniversitySpecification {
    public static Specification<University> getUniversitySpecification(String name, String country, String department) {
        return (root, query, criteriaBuilder) -> {
            if (name != null && !name.isEmpty()) {
                return criteriaBuilder.like(root.get("name"), "%" + name + "%");
            }
            if (country != null && !country.isEmpty()) {
                return criteriaBuilder.like(root.get("country"), "%" + country + "%");
            }
            if (department != null && !department.isEmpty()) {
                return criteriaBuilder.like(root.get("department"), "%" + department + "%");
            }
            return criteriaBuilder.conjunction();
        };
    }
}