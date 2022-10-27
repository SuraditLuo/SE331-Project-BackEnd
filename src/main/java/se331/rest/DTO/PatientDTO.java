package se331.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PatientDTO {
    Long id;
    String name;
    String surname;
    String age;
    String hometown;
    String status;
    PatientDoctorDTO doctor;
    List<PatientCommentDTO> comments;
    List<PatientVaccineDTO> vaccines;
    List<String> imageUrls;
}
