package org.example.bonnesante_api.Repository;

import org.example.bonnesante_api.Entity.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<PatientEntity, Long> {
    PatientEntity findByUserId(Long id);

}
