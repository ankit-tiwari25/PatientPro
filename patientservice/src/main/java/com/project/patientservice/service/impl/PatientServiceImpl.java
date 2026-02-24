package com.project.patientservice.service.impl;

import com.project.patientservice.dto.request.PatientRequestDTO;
import com.project.patientservice.dto.response.PatientResponseDTO;
import com.project.patientservice.exception.EmailAlreadyExistsException;
import com.project.patientservice.exception.PatientNotFoundException;
import com.project.patientservice.mapper.PatientMapper;
import com.project.patientservice.model.Patient;
import com.project.patientservice.repository.PatientRepository;
import com.project.patientservice.service.PatientService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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
        if(patientRepository.existsByEmail(patient.getEmail())){
            throw new EmailAlreadyExistsException("Patient with email already exists.");
        }
        Patient savedPatient = patientRepository.save(patient);
        return toPatientResponseDTO(savedPatient);
    }

    public PatientResponseDTO updatePatient(UUID uuid, PatientRequestDTO patientRequestDTO){
        Patient existingPatient = patientRepository.findById(uuid)
                .orElseThrow(() -> new PatientNotFoundException("Patient not found with id: " + uuid));

        if(patientRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), uuid)){
            throw new EmailAlreadyExistsException("Patient with email already exists.");
        }
        existingPatient.setName(patientRequestDTO.getName());
        existingPatient.setEmail(patientRequestDTO.getEmail());
        existingPatient.setAddress(patientRequestDTO.getAddress());
        existingPatient.setDateOfBirth(java.time.LocalDate.parse(patientRequestDTO.getDateOfBirth()));

        Patient updatedPatient = patientRepository.save(existingPatient);
        return toPatientResponseDTO(updatedPatient);
    }

}
