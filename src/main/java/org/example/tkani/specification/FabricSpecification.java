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
}
