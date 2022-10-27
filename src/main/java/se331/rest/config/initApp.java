package se331.rest.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import se331.rest.entity.Patient;
import se331.rest.entity.Doctor;
import se331.rest.entity.Vaccine;
import se331.rest.repository.PatientRepository;
import se331.rest.repository.DoctorRepository;
import se331.rest.repository.VaccineRepository;
import se331.rest.security.entity.Authority;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;
import se331.rest.security.repository.AuthorityRepository;
import se331.rest.security.repository.UserRepository;

import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

@Component
public class initApp implements ApplicationListener<ApplicationReadyEvent> {
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    VaccineRepository vaccineRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    AuthorityRepository authorityRepository;

    @Override
    @Transactional
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Vaccine v1, v2, v3, v4, v5;
        v1 = vaccineRepository.save(Vaccine.builder()
                .name("SinoPharm").build());
        v2 = vaccineRepository.save(Vaccine.builder()
                .name("Pfizer").build());
        v3 = vaccineRepository.save(Vaccine.builder()
                .name("Astra zeneca").build());
        v4 = vaccineRepository.save(Vaccine.builder()
                .name("Moderna").build());
        v5 = vaccineRepository.save(Vaccine.builder()
                .name("Johnson & Johnson").build());
        Doctor d1, d2, d3;
        d1 = doctorRepository.save(Doctor.builder()
                .name("James").build());
        d3 = doctorRepository.save(Doctor.builder()
                .name("David").build());
        Patient tempPatient = null;
        tempPatient = patientRepository.save(Patient.builder()
                .name("Suradit")
                .surname("Luo")
                .age("20")
                .hometown("Chiang Rai")
                .status("already get second dose")
                .build());
        tempPatient.getVaccines().add(v2);
        tempPatient.getVaccines().add(v2);
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient = patientRepository.save(Patient.builder()
                .name("Jack")
                .surname("Ma")
                .age("56")
                .hometown("BeiJing")
                .status("already get second dose")
                .build());
        tempPatient.getVaccines().add(v3);
        tempPatient.getVaccines().add(v5);
        d1.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d1);
        addUser();
        d1.setUser(user1);
        user1.setDoctor(d1);
        d3.setUser(user3);
        user3.setDoctor(d3);
    }
    User user1, user2, user3;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser =
                Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin =
                Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor =
                Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 04, 21)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user2 = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .firstname("user")
                .lastname("user")
                .email("enabled@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 07, 25)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();

        user3 = User.builder()
                .username("doctor")
                .password(encoder.encode("doctor"))
                .firstname("doctor")
                .lastname("doctor")
                .email("doctor@user.com")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user1.getAuthorities().add(authDoctor);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);

        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }
}
