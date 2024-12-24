package org.example.tkani.repository;

import org.example.tkani.model.Fabric;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FabricRepository extends JpaRepository<Fabric, Long>, JpaSpecificationExecutor<Fabric> {

    Page<Fabric> findAll(Specification<Fabric> specification, Pageable pageable);
}