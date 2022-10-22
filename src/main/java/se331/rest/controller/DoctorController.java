package se331.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import se331.rest.entity.Doctor;
import se331.rest.util.ProjectMapper;

@Controller
public class DoctorController {
    @Autowired
    DoctorService doctorService;

    @GetMapping("doctor")
    public ResponseEntity<?> getDoctorLists(@RequestParam(value = "_limit", required = false) Integer perPage
            , @RequestParam(value = "_page", required = false) Integer page) {
        perPage = perPage == null ? 3 : perPage;
        page = page == null ? 1 : page;
        Page<Doctor> pageOutput;
        pageOutput = doctorService.getDoctors(perPage, page);
        HttpHeaders responseHeader = new HttpHeaders();

        responseHeader.set("x-total-count", String.valueOf(pageOutput.getTotalElements()));
        return new ResponseEntity<>(ProjectMapper.INSTANCE.getDoctorDTO(pageOutput.getContent()), responseHeader, HttpStatus.OK);


    }

    @GetMapping("doctor/{id}")
    public ResponseEntity<?> getDoctor(@PathVariable("id") Long id) {
        Doctor output = doctorService.getDoctor(id);
        if (output != null) {
            return ResponseEntity.ok(ProjectMapper.INSTANCE.getDoctorDTO(output));
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "The given id is not found");
        }
    }
}
