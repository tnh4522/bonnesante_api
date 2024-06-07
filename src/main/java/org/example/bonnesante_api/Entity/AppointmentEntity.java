package org.example.bonnesante_api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "appointment")
@Getter
@Setter
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column(name = "patient_id")
    private long patientId;
    @Column(name = "doctor_id")
    private long doctorId;
    @Column(name = "schedule_id")
    private long scheduleId;
    @Column(name = "date")
    private String date;
    @Column(name = "start_time")
    private String startTime;
    @Column(name = "end_time")
    private String endTime;
    @Column(name = "description")
    private String description;
    @Column(name = "status")
    private String status = "Scheduled";

    @OneToOne

    private DoctorEntity doctorEntity;

    @OneToOne
    private PatientEntity patientEntity;
}