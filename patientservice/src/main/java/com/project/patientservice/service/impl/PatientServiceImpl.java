package com.project.patientservice.service.impl;

import com.project.patientservice.dto.request.PatientRequestDTO;
import com.project.patientservice.dto.response.PatientResponseDTO;
import com.project.patientservice.mapper.PatientMapper;
import com.project.patientservice.model.Patient;
import com.project.patientservice.repository.PatientRepository;
import com.project.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.project.patientservice.mapper.PatientMapper.toPatientResponseDTO;

@Service
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;

    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponseDTO> getAllPatients() {
        List<Patient> patients =  patientRepository.findAll();

        return patients.stream()
                .map(PatientMapper::toPatientResponseDTO).toList();
    }

   public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = PatientMapper.toPatientFromRequestDTO(patientRequestDTO);
        Patient savedPatient = patientRepository.save(patient);
        return toPatientResponseDTO(savedPatient);
    }

}
