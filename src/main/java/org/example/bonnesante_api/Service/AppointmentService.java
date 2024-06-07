package org.example.bonnesante_api.Service;

import org.example.bonnesante_api.Entity.AppointmentEntity;

import java.util.List;

public interface AppointmentService {
    AppointmentEntity makeAppointment(AppointmentEntity appointmentEntity);

    List<AppointmentEntity> getAppointmentByPatientId(Long id);

    AppointmentEntity getAppointmentById(Long id);

}