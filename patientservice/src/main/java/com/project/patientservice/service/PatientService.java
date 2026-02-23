package com.project.patientservice.service;

import com.project.patientservice.dto.response.PatientResponseDTO;

import java.util.List;

public interface PatientService {
    List<PatientResponseDTO> getAllPatients();
}
