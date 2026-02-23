package com.project.patientservice.service;

import com.project.patientservice.dto.request.PatientRequestDTO;
import com.project.patientservice.dto.response.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    List<PatientResponseDTO> getAllPatients();
    PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO);
}
