package org.example.bonnesante_api.Service.implement;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Entity.ScheduleEntity;
import org.example.bonnesante_api.Repository.AppointmentRepository;
import org.example.bonnesante_api.Repository.PatientRepository;
import org.example.bonnesante_api.Repository.ScheduleRepository;
import org.example.bonnesante_api.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public List<ScheduleEntity> getAllSchedule(Long doctorId, String date) {
        return scheduleRepository.findAllByDoctorIdAndDate(doctorId, date);
    }

    @Override
    public List<AppointmentEntity> getAppointment(Long id) {
        List<AppointmentEntity> appointmentEntities = appointmentRepository.getAllByDoctorId(id);
        for (AppointmentEntity appointmentEntity : appointmentEntities) {
            PatientEntity patientEntity = patientRepository.findById(appointmentEntity.getPatientId()).get();
            appointmentEntity.setPatientEntity(patientEntity);
        }
        return appointmentEntities;
    }

    @Override
    public AppointmentEntity updateAppointmentStatus(Long id, String status) {
        return appointmentRepository.findById(id).map(appointmentEntity -> {
            appointmentEntity.setStatus(status);
            LocalDateTime date = LocalDateTime.now();
            if (status.equals("denied")) {
                appointmentEntity.setEndTime(date.getHour() + ":" + date.getMinute());
            }
            if (status.equals("waiting")) {
                String dateAppointment = appointmentEntity.getDate();
                String timeAppointment = appointmentEntity.getStartTime();
                List<AppointmentEntity> appointmentEntities = appointmentRepository.getAllByDoctorId(appointmentEntity.getDoctorId());
                for (AppointmentEntity appointment : appointmentEntities) {
                    if (appointment.getDate().equals(dateAppointment) && appointment.getStartTime().equals(timeAppointment) && appointment.getId() != id) {
                        appointment.setStatus("denied");
                        appointment.setEndTime(date.getHour() + ":" + date.getMinute());
                        appointmentRepository.save(appointment);
                    }
                }
            }
            return appointmentRepository.save(appointmentEntity);
        }).orElse(null);
    }


}
