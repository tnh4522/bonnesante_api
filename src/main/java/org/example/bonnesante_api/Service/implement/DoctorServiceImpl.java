package org.example.bonnesante_api.Service.implement;

import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Repository.DoctorRepository;
import org.example.bonnesante_api.Service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public List<DoctorEntity> getAllDoctors() {
        return doctorRepository.findAll();
    }
}
