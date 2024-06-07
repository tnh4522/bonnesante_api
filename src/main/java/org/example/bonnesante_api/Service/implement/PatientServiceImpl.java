package org.example.bonnesante_api.Service.implement;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Entity.ScheduleEntity;
import org.example.bonnesante_api.Repository.AppointmentRepository;
import org.example.bonnesante_api.Repository.DoctorRepository;
import org.example.bonnesante_api.Repository.PatientRepository;
import org.example.bonnesante_api.Repository.ScheduleRepository;
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
        patientEntity.setListDoctor(doctorId);
        return patientRepository.save(patientEntity);
    }

    @Override
    public List<DoctorEntity> getDoctorList(Long id) {
        String doctorList = patientRepository.findById(id).get().getListDoctor();

        if (doctorList == null) {
            return null;
        }

        Gson gson = new Gson();
        JsonObject jsonObject = gson.fromJson(doctorList, JsonObject.class);
        JsonArray doctorIdsArray = jsonObject.getAsJsonArray("doctorIds");

        int[] doctorIds = new int[doctorIdsArray.size()];
        List<DoctorEntity> doctorEntities = new ArrayList<>();

        for (int i = 0; i < doctorIdsArray.size(); i++) {
            doctorIds[i] = doctorIdsArray.get(i).getAsInt();
            assert false;
            doctorEntities.add(doctorRepository.findById((long)doctorIds[i]).get());
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
            scheduleEntity.setStatus("Unavailable");
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
}
