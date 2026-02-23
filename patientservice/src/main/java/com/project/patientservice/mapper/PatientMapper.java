package com.project.patientservice.mapper;

import com.project.patientservice.dto.response.PatientResponseDTO;
import com.project.patientservice.model.Patient;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientResponseDTO = new PatientResponseDTO();
        patientResponseDTO.setId(patient.getId().toString());
        patientResponseDTO.setName(patient.getName());
        patientResponseDTO.setEmail(patient.getEmail());
        patientResponseDTO.setAddress(patient.getAddress());
        patientResponseDTO.setDateOfBirth(patient.getDateOfBirth().toString());
        return patientResponseDTO;
    }

    public static Patient toPatient(PatientResponseDTO patientResponseDTO) {
        Patient patient = new Patient();
        patient.setId(patientResponseDTO.getId() != null ? java.util.UUID.fromString(patientResponseDTO.getId()) : null);
        patient.setName(patientResponseDTO.getName());
        patient.setEmail(patientResponseDTO.getEmail());
        patient.setAddress(patientResponseDTO.getAddress());
        patient.setDateOfBirth(java.time.LocalDate.parse(patientResponseDTO.getDateOfBirth()));
        return patient;
    }
}
