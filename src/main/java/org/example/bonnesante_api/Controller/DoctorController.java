package org.example.bonnesante_api.Controller;

import org.example.bonnesante_api.Entity.AppointmentEntity;
import org.example.bonnesante_api.Entity.DoctorEntity;
import org.example.bonnesante_api.Entity.ScheduleEntity;
import org.example.bonnesante_api.Service.DoctorService;
import org.example.bonnesante_api.Service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/doctor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/list")
    public ResponseEntity<List<DoctorEntity>> getListDoctor() {
        return ResponseEntity.ok(doctorService.getAllDoctors());
    }

    @PostMapping("/{id}/schedule")
    public ResponseEntity<List<ScheduleEntity>> getSchedule(@PathVariable Long id, @RequestBody String date) {
        String dateDecode = URLDecoder.decode(date);

        dateDecode = dateDecode.substring(0, dateDecode.length() - 1);
        return ResponseEntity.ok(scheduleService.getAllSchedule(id, dateDecode));
    }

    @GetMapping("{id}/appointment")
    public ResponseEntity<List<AppointmentEntity>> getAppointment(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getAppointment(id));
    }

    @PostMapping("/update/appointment/{id}/status")
    public ResponseEntity<AppointmentEntity> updateAppointmentStatus(@PathVariable Long id, @RequestBody String status) {
        status = status.substring(0, status.length() - 1);
        return ResponseEntity.ok(scheduleService.updateAppointmentStatus(id, status));
    }
}
