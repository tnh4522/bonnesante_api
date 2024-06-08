package org.example.bonnesante_api.Entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "register_doctor")
@Getter
@Setter
public class RegisterDoctorEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "doctor_id")
    private Long doctorId;
    @Column(name = "patient_id")
    private Long patientId;
    @Column(name = "status")
    private String status;
    @OneToOne
    private PatientEntity patientEntity;
}
