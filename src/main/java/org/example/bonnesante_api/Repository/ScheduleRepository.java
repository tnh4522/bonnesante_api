package org.example.bonnesante_api.Repository;

import org.example.bonnesante_api.Entity.ScheduleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<ScheduleEntity, Long> {
    List<ScheduleEntity> findAllByDoctorIdAndDate(Long doctorId, String date);
}
