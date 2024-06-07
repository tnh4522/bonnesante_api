package org.example.bonnesante_api.Service.implement;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Repository.AppointmentRepository;
import org.example.bonnesante_api.Repository.DoctorRepository;
import org.example.bonnesante_api.Repository.PatientRepository;
import org.example.bonnesante_api.Service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentServiceImpl implements AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Override
    public AppointmentEntity makeAppointment(AppointmentEntity appointmentEntity) {
        return appointmentRepository.save(appointmentEntity);
    }

    @Override
    public List<AppointmentEntity> getAppointmentByPatientId(Long id) {
        List<AppointmentEntity> listAppointment = appointmentRepository.findAll();
        listAppointment.removeIf(appointmentEntity -> appointmentEntity.getPatientId() != id);
        return listAppointment;
    }

    @Override
    public AppointmentEntity getAppointmentById(Long id) {
        AppointmentEntity appointmentEntity = appointmentRepository.findById(id).orElse(null);
        DoctorEntity doctorEntity = doctorRepository.findById(appointmentEntity.getDoctorId()).orElse(null);
        PatientEntity patientEntity = patientRepository.findById(appointmentEntity.getPatientId()).orElse(null);
        appointmentEntity.setDoctorEntity(doctorEntity);
        appointmentEntity.setPatientEntity(patientEntity);
        return appointmentEntity;
    }
}
