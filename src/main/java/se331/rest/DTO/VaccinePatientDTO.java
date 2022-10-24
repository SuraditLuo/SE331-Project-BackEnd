package se331.rest.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VaccinePatientDTO {
    Long id;
    String name;
    String surname;
    String age;
    String hometown;
    String status;
}
