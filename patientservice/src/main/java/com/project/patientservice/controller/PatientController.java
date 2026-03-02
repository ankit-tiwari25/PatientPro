package com.project.patientservice.controller;

import com.project.patientservice.dto.request.PatientRequestDTO;
import com.project.patientservice.dto.response.PatientResponseDTO;
import com.project.patientservice.dto.validators.CreatePatientValidationGroup;
import com.project.patientservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.groups.Default;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/patients")
@Tag(name = "Patient", description = "Endpoints for managing patients")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieve a list of all patients")
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients() {
        List<PatientResponseDTO> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieve a patient by their unique ID")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable String id) {
       PatientResponseDTO patient = patientService.getPatientById(java.util.UUID.fromString(id));
         return ResponseEntity.ok(patient);
    }

    @PostMapping
    @Operation(summary = "Create a new patient", description = "Create a new patient with the provided details")
    public ResponseEntity<PatientResponseDTO> createPatient(@Validated({Default.class, CreatePatientValidationGroup.class})  @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO createdPatient = patientService.createPatient(patientRequestDTO);
        return ResponseEntity.ok(createdPatient);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an existing patient", description = "Update the details of an existing patient by their unique ID")
    public ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable String id, @Validated(Default.class) @RequestBody PatientRequestDTO patientRequestDTO) {
        PatientResponseDTO updatedPatient = patientService.updatePatient(java.util.UUID.fromString(id), patientRequestDTO);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a patient", description = "Delete an existing patient by their unique ID")
    public ResponseEntity<Void> deletePatient(@PathVariable String id) {
        patientService.deletePatient(java.util.UUID.fromString(id));
        return ResponseEntity.noContent().build();
    }

}
