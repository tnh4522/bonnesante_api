package org.example.bonnesante_api.Controller;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.PatientEntity;
import org.example.bonnesante_api.Entity.UserEntity;
import org.example.bonnesante_api.Service.AppointmentService;
import org.example.bonnesante_api.Service.PatientService;
import org.example.bonnesante_api.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/patient")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<PatientEntity> getPatientByUserId(@PathVariable Long id) {
        PatientEntity patientEntity = patientService.getPatientByUserId(id);
        if(patientEntity != null) {
            return ResponseEntity.ok(patientEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/register/healthID/{healthID}")
    public ResponseEntity<ArrayList<Object>> registerByHealthID(@PathVariable String healthID) {
        PatientEntity patientEntity = patientService.getPatientByHealthID(healthID);
        UserEntity userEntity = userService.getUserByID(patientEntity.getUserId());
        ArrayList<Object> arrayList = new ArrayList<>();
        arrayList.add(patientEntity);
        userEntity.setPassword("");
        arrayList.add(userEntity);
        return ResponseEntity.ok(arrayList);
    }

    @PostMapping("/save")
    public ResponseEntity<PatientEntity> savePatient(@RequestBody PatientEntity patient) {
        PatientEntity patientEntity = patientService.savePatient(patient);
        return ResponseEntity.ok(patientEntity);
    }

    @PostMapping("/appointment")
    public ResponseEntity<AppointmentEntity> addAppointment(@RequestBody Object appointment) {
        LinkedHashMap<String, Object> appointmentMap = (LinkedHashMap<String, Object>) appointment;
        Long patientId = Long.parseLong(appointmentMap.get("patientId").toString());
        Long doctorId = Long.parseLong(appointmentMap.get("doctorId").toString());
        String date = appointmentMap.get("date").toString();
        List<String> timeSlot = (List<String>) appointmentMap.get("timeSlot");
        boolean scheduleAppointment = patientService.scheduleAppointment(patientId, doctorId, date, timeSlot);
        if (scheduleAppointment) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/appointment/{id}")
    public ResponseEntity<List<AppointmentEntity>> getAppointmentById(@PathVariable Long id) {
        List<AppointmentEntity> appointmentEntity = appointmentService.getAppointmentByPatientId(id);
        return ResponseEntity.ok(appointmentEntity);
    }

    @GetMapping("/appointment/detail/{id}")
    public ResponseEntity<AppointmentEntity> getAppointmentDetail(@PathVariable Long id) {
        AppointmentEntity appointmentEntity = appointmentService.getAppointmentById(id);
        return ResponseEntity.ok(appointmentEntity);
    }

    @PostMapping("/{id}/register/doctor")
    public ResponseEntity<PatientEntity> registerDoctor(@PathVariable Long id, @RequestBody String doctorId) {
        PatientEntity patientEntity = patientService.registerDoctor(id, doctorId);
        if(patientEntity != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{id}/doctor/update")
    public ResponseEntity<PatientEntity> updateDoctorList(@PathVariable Long id, @RequestBody String doctorId) {
        PatientEntity patientEntity = patientService.registerDoctor(id, doctorId);
        if(patientEntity != null) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}/doctor/list")
    public ResponseEntity<List<DoctorEntity>> getDoctorList(@PathVariable Long id) {
        List<DoctorEntity> doctorEntity = patientService.getDoctorList(id);
        if (doctorEntity != null) {
            return ResponseEntity.ok(doctorEntity);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
