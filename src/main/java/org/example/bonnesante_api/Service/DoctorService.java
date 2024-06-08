package org.example.bonnesante_api.Service;

import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Entity.RegisterDoctorEntity;

import java.util.List;

public interface DoctorService {
    List<DoctorEntity> getAllDoctors();

    List<RegisterDoctorEntity> getPatientByDoctorId(Long id);

    RegisterDoctorEntity updateRegisterPatient(Long id, RegisterDoctorEntity registerDoctorEntity);
}
