package org.example.bonnesante_api.Repository;

import org.example.bonnesante_api.Entity.RegisterDoctorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RegisterDoctorRepository extends JpaRepository<RegisterDoctorEntity, Long> {
    List<RegisterDoctorEntity> findByDoctorId(Long id);

    List<RegisterDoctorEntity> findByPatientId(Long id);
}
