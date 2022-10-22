package se331.rest.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import se331.rest.DTO.VaccineDTO;
import se331.rest.security.dto.DoctorAuthDTO;
import se331.rest.DTO.DoctorDTO;
import se331.rest.DTO.PatientDTO;
import se331.rest.entity.*;
import se331.rest.security.entity.User;
import se331.rest.security.dto.UserDTO;

import java.util.List;
import java.util.stream.Collectors;


@Mapper(imports = Collectors.class)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    PatientDTO getPatientDTO(Patient patient);

    UserDTO getUserDTO(User user);

    List<PatientDTO> getPatientDTO(List<Patient> patients);

    DoctorDTO getDoctorDTO(Doctor doctor);

    List<DoctorDTO> getDoctorDTO(List<Doctor> doctors);
    VaccineDTO getVaccineDTO(Vaccine vaccine);

    @Mapping(target = "authorities", expression = "java(doctor.getUser().getAuthorities().stream().map(auth -> auth.getName().name()).collect(Collectors.toList()))")
    DoctorAuthDTO getDoctorAuthDTO(Doctor doctor);
}
