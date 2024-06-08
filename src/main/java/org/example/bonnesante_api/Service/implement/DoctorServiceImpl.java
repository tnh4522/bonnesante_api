package org.example.bonnesante_api.Service.implement;

import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Entity.RegisterDoctorEntity;
import org.example.bonnesante_api.Repository.DoctorRepository;
import org.example.bonnesante_api.Repository.PatientRepository;
import org.example.bonnesante_api.Repository.RegisterDoctorRepository;
import org.example.bonnesante_api.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private RegisterDoctorRepository registerDoctorRepository;

    @Override
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @Override
    public List<RegisterDoctorEntity> getPatientByDoctorId(Long id) {
        List<RegisterDoctorEntity> registerDoctorEntities = registerDoctorRepository.findByDoctorId(id);
        for (RegisterDoctorEntity registerDoctorEntity : registerDoctorEntities) {
            PatientEntity patientEntity = patientRepository.findById(registerDoctorEntity.getPatientId()).orElse(null);
            registerDoctorEntity.setPatientEntity(patientEntity);
        }
        return registerDoctorEntities;
    }

    @Override
    public RegisterDoctorEntity updateRegisterPatient(Long id, RegisterDoctorEntity registerDoctorEntity) {
        RegisterDoctorEntity registerDoctorEntity1 = registerDoctorRepository.findById(id).orElse(null);
        if (registerDoctorEntity1 != null) {
            registerDoctorEntity1.setStatus(registerDoctorEntity.getStatus());
            registerDoctorRepository.save(registerDoctorEntity1);
            return registerDoctorEntity1;
        }
        return null;
    }

}
