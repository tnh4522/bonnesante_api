package org.example.bonnesante_api.Service;

import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;

import java.util.ArrayList;
import java.util.List;

public interface PatientService {
    PatientEntity savePatient(PatientEntity patient);

    List<PatientEntity> getAllPatients();

    PatientEntity getPatientByUserId(Long id);

    PatientEntity updatePatient(PatientEntity patient);

    void deletePatient(Long id);

    PatientEntity registerDoctor(Long id, String doctorId);

    List<DoctorEntity> getDoctorList(Long id);

    boolean scheduleAppointment(Long patientId, Long doctorId, String date, List<String> timeSlot);

    PatientEntity getPatientByHealthID(String healthID);
}
