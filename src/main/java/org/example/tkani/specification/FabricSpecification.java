package org.example.tkani.specification;

import org.example.tkani.model.Fabric;
import org.springframework.data.jpa.domain.Specification;

public class FabricSpecification {

    public static Specification<Fabric> bySubcategoryId(Long subcategoryId) {
        return (r, q, cb) -> {
            if (subcategoryId == 0) return cb.conjunction();
            return cb.equal(r.get("subcategory").get("id"), subcategoryId);
        };
    }

    public static Specification<Fabric> isEnabled(Boolean isEnabled) {
        return (r, q, cb) -> {
            if (isEnabled == null) return cb.conjunction();
            return cb.equal(r.get("enabled"), isEnabled);
        };
    }

    public static Specification<Fabric> bySearch(String search) {
        return (root, query, criteriaBuilder) -> {
            if (search == null || search.isBlank()) {
                return criteriaBuilder.conjunction();
            }
            String pattern = "%" + search.trim().toLowerCase() + "%";
            return criteriaBuilder.or(
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), pattern),
                    criteriaBuilder.like(criteriaBuilder.lower(root.get("description")), pattern)
            );
        };
    }

    public static Specification<Fabric> byCategoryId(Long categoryId) {
        return (r, q, cb) -> {
            if (categoryId == 0) return cb.conjunction();
            return cb.equal(r.get("subcategory").get("category").get("id"), categoryId);
        };
    }
}
