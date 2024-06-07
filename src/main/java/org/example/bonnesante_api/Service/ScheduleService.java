package org.example.bonnesante_api.Service;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.ScheduleEntity;

import java.util.List;

public interface ScheduleService {
    List<ScheduleEntity> getAllSchedule(Long doctorId, String date);

    List<AppointmentEntity> getAppointment(Long id);

    AppointmentEntity updateAppointmentStatus(Long id, String status);
}
