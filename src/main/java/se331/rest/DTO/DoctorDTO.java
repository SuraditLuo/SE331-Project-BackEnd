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
public class DoctorDTO {
    Long id;
    String firstname;
    String lastname;
    List<DoctorChargePatientsDTO> inCharge = new ArrayList<>();

}

