package com.project.patientservice.service;

import com.project.patientservice.dto.request.PatientRequestDTO;
import com.project.patientservice.dto.response.PatientResponseDTO;
import jakarta.validation.Valid;

import java.util.List;
import java.util.UUID;

public interface PatientService {
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
    PatientResponseDTO updatePatient(UUID uuid, @Valid PatientRequestDTO patientRequestDTO);
}
