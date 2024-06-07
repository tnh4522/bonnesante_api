package org.example.bonnesante_api.Repository;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> getAllByDoctorId(Long doctorId);
}
