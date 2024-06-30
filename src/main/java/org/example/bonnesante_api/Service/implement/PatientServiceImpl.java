package org.example.bonnesante_api.Service.implement;

import com.google.gson.JsonParser;
import org.example.bonnesante_api.Entity.*;
import org.example.bonnesante_api.Repository.*;
import org.example.bonnesante_api.Service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private RegisterDoctorRepository registerDoctorRepository;
    @Override
    public PatientEntity savePatient(PatientEntity patient) {
        return patientRepository.save(patient);
    }

    @Override
    public List<PatientEntity> getAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public PatientEntity getPatientByUserId(Long id) {
        return patientRepository.findByUserId(id);
    }

    @Override
    public PatientEntity updatePatient(PatientEntity patient) {
        return null;
    }

    @Override
    public void deletePatient(Long id) {

    }

    @Override
    public PatientEntity registerDoctor(Long id, String doctorId) {
        PatientEntity patientEntity = patientRepository.findById(id).get();

        Gson gson = new Gson();
        JsonObject jsonObject = JsonParser.parseString(doctorId).getAsJsonObject();
        JsonArray doctorIdsArray = jsonObject.getAsJsonArray("doctorIds");

        for (int i = 0; i < doctorIdsArray.size(); i++) {
            int doctorIdInt = doctorIdsArray.get(i).getAsInt();

            RegisterDoctorEntity registerDoctorEntity = new RegisterDoctorEntity();
            registerDoctorEntity.setDoctorId((long)doctorIdInt);
            registerDoctorEntity.setPatientId(id);
            registerDoctorEntity.setStatus("pending");

            registerDoctorRepository.save(registerDoctorEntity);
        }

        patientEntity.setListDoctor(doctorId);
        return patientRepository.save(patientEntity);
    }

    @Override
    public List<DoctorEntity> getDoctorList(Long id) {
        List<RegisterDoctorEntity> registerDoctorEntities = registerDoctorRepository.findByPatientId(id);
        List<DoctorEntity> doctorEntities = new ArrayList<>();
        for (RegisterDoctorEntity registerDoctorEntity : registerDoctorEntities) {
            if (registerDoctorEntity.getStatus().equals("registered")) {
                DoctorEntity doctorEntity = doctorRepository.findById(registerDoctorEntity.getDoctorId()).get();
                doctorEntities.add(doctorEntity);
            }
        }

        return doctorEntities;
    }

    @Override
    public boolean scheduleAppointment(Long patientId, Long doctorId, String date, List<String> timeSlot) {
        for (String time : timeSlot) {
            ScheduleEntity scheduleEntity = new ScheduleEntity();
            scheduleEntity.setDoctorId(doctorId);
            scheduleEntity.setDate(date);
            scheduleEntity.setTime(time);
            scheduleEntity.setStatus("Available");
            long scheduleID = scheduleRepository.save(scheduleEntity).getId();

            AppointmentEntity appointmentEntity = new AppointmentEntity();
            appointmentEntity.setPatientId(patientId);
            appointmentEntity.setDoctorId(doctorId);
            appointmentEntity.setScheduleId(scheduleID);
            appointmentEntity.setDate(date);
            appointmentEntity.setStartTime(time);
            appointmentEntity.setStatus("pending");
            appointmentRepository.save(appointmentEntity);
        }
        return true;
    }

    @Override
    public PatientEntity getPatientByHealthID(String healthID) {
        return patientRepository.findByHealthId(healthID);
    }
}
