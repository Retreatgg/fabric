package org.example.tkani.specification;

import org.example.tkani.model.Fabric;
import org.springframework.data.jpa.domain.Specification;

public class FabricSpecification {

    public static Specification<Fabric> byCategoryId(Long categoryId) {
        return (r, q, cb) -> {
            if (categoryId == 0) return cb.conjunction();
            return cb.equal(r.get("category").get("id"), categoryId);
        };
    }

    public static Specification<Fabric> isEnabled(Boolean isEnabled) {
        return (r, q, cb) -> {
            if (isEnabled == null) return cb.conjunction();
            return cb.equal(r.get("enabled"), isEnabled);
        };
    }
}
