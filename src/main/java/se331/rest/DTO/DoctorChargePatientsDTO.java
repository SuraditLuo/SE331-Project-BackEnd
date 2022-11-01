package se331.rest.DTO;

import lombok.*;

import javax.persistence.Entity;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorChargePatientsDTO {
    Long id;
    String firstname;
    String lastname;
    String age;
    String hometown;
    String status;

}
