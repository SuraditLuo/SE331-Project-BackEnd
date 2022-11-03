package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Patient;
import se331.rest.service.PatientService;
import se331.rest.util.ProjectMapper;

@Controller
public class PatientController {
    @Autowired
    PatientService patientService;

    @GetMapping("patient")
    public ResponseEntity<?> getPatientLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page
            , @RequestParam(value = "name", required = false) String name) {
        perPage = perPage == null ? 20 : perPage;
        page = page == null ? 1 : page;
        Page<Patient> pageOutput;
        if (name == null) {
            pageOutput = patientService.getPatients(perPage, page);
        } else {
            pageOutput = patientService.getPatients(name, PageRequest.of(page - 1, perPage));
        }
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getPatientDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);


    }

    @GetMapping("patient/{id}")
    public ResponseEntity<?> getPatient(@PathVariable("id") Long id) {
        Patient output = patientService.getPatient(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }

    @PostMapping("/save-doctor")
    public ResponseEntity<?> addDoctor(@RequestBody Patient patient) throws IndexOutOfBoundsException {
        Patient output = patientService.saveDoctor(patient);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDTO(output));
    }
    @PostMapping("/save-vaccine")
    public ResponseEntity<?> addVaccine(@RequestBody Patient patient) throws IndexOutOfBoundsException {
        Patient output = patientService.saveVaccine(patient);
        return ResponseEntity.ok(ProjectMapper.INSTANCE.getPatientDTO(output));
    }
}
