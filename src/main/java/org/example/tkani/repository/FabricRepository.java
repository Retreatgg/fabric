package org.example.tkani.repository;

import org.example.tkani.model.Fabric;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface FabricRepository extends JpaRepository<Fabric, Long>, JpaSpecificationExecutor<Fabric> {
}