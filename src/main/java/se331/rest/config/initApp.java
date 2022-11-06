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
        d3 = doctorRepository.save(Doctor.builder()
                .firstname("doctor")
                .lastname("doctor")
                .build());
        d2 = doctorRepository.save(Doctor.builder()
                .firstname("Black")
                .lastname("Jack")
                .build());
        addUser();
        d2.setUser(user5);
        user5.setDoctor(d2);
        d3.setUser(user3);
        user3.setDoctor(d3);
        Patient tempPatient = null;
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user13.getFirstname())
                .lastname(user13.getLastname())
                .age(user13.getAge())
                .address(user13.getAddress())
                .status("already get second doses")
                .build());
        tempPatient.getVaccines().add(v2);
        tempPatient.getVaccines().add(v2);
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient.setUser(user13);
        user13.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user14.getFirstname())
                .lastname(user14.getLastname())
                .age(user14.getAge())
                .address(user14.getAddress())
                .status("already get second doses")
                .build());
        tempPatient.getVaccines().add(v3);
        tempPatient.getVaccines().add(v5);
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.setUser(user14);
        user14.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user15.getFirstname())
                .lastname(user15.getLastname())
                .age(user15.getAge())
                .address(user15.getAddress())
                .status("get only one dose")
                .build());
        tempPatient.setUser(user15);
        user15.setPatient(tempPatient);
        tempPatient.getVaccines().add(v5);
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user16.getFirstname())
                .lastname(user16.getLastname())
                .age(user16.getAge())
                .address(user16.getAddress())
                .status("get only one dose")
                .build());
        tempPatient.getVaccines().add(v2);
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient.setUser(user16);
        user16.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user17.getFirstname())
                .lastname(user17.getLastname())
                .age(user17.getAge())
                .address(user17.getAddress())
                .status("already get second doses")
                .build());
        tempPatient.getVaccines().add(v4);
        tempPatient.getVaccines().add(v4);
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient.setUser(user17);
        user17.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user4.getFirstname())
                .lastname(user4.getLastname())
                .age(user4.getAge())
                .address(user4.getAddress())
                .build());
        d3.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d3);
        tempPatient.setUser(user4);
        user4.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user6.getFirstname())
                .lastname(user6.getLastname())
                .age(user6.getAge())
                .address(user6.getAddress())
                .build());
        tempPatient.setUser(user6);
        user6.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user7.getFirstname())
                .lastname(user7.getLastname())
                .age(user7.getAge())
                .address(user7.getAddress())
                .status("get only one dose")
                .build());
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.getVaccines().add(v3);
        tempPatient.setUser(user7);
        user7.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user8.getFirstname())
                .lastname(user8.getLastname())
                .age(user8.getAge())
                .address(user8.getAddress())
                .build());
        tempPatient.setUser(user8);
        user8.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user9.getFirstname())
                .lastname(user9.getLastname())
                .age(user9.getAge())
                .address(user9.getAddress())
                .status("get only one dose")
                .build());
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.getVaccines().add(v2);
        tempPatient.setUser(user9);
        user9.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user10.getFirstname())
                .lastname(user10.getLastname())
                .age(user10.getAge())
                .address(user10.getAddress())
                .status("already get second doses")
                .build());
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.getVaccines().add(v1);
        tempPatient.getVaccines().add(v2);
        tempPatient.setUser(user10);
        user10.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user11.getFirstname())
                .lastname(user11.getLastname())
                .age(user11.getAge())
                .address(user11.getAddress())
                .status("get only one dose")
                .build());
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.getVaccines().add(v5);
        tempPatient.setUser(user11);
        user11.setPatient(tempPatient);
        tempPatient = patientRepository.save(Patient.builder()
                .firstname(user12.getFirstname())
                .lastname(user12.getLastname())
                .age(user12.getAge())
                .address(user12.getAddress())
                .status("already get second doses")
                .build());
        d2.getInCharge().add(tempPatient);
        tempPatient.setDoctor(d2);
        tempPatient.getVaccines().add(v1);
        tempPatient.getVaccines().add(v1);
        tempPatient.setUser(user12);
        user12.setPatient(tempPatient);
    }
    User user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15, user16, user17;
    private void addUser() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        Authority authUser =
                Authority.builder().name(AuthorityName.ROLE_USER).build();
        Authority authAdmin =
                Authority.builder().name(AuthorityName.ROLE_ADMIN).build();
        Authority authDoctor =
                Authority.builder().name(AuthorityName.ROLE_DOCTOR).build();
        Authority authPatient =
                Authority.builder().name(AuthorityName.ROLE_PATIENT).build();
        user1 = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .firstname("admin")
                .lastname("admin")
                .email("admin@admin.com")
                .age("50")
                .address("Chiang Mai")
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
                .age("28")
                .address("Chiang Mai")
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
                .age("49")
                .address("Chiang Mai")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user4 = User.builder()
                .username("patient")
                .password(encoder.encode("patient"))
                .firstname("patient")
                .lastname("patient")
                .email("patient@user.com")
                .age("40")
                .address("Chiang Rai")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user5 = User.builder()
                .username("BJ_1nwza")
                .password(encoder.encode("123"))
                .firstname("Black")
                .lastname("Jack")
                .email("BJ@mac.com")
                .age("30")
                .address("6087 Elgar Terrace")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user6 = User.builder()
                .username("jcastellb")
                .password(encoder.encode("123"))
                .firstname("Joye")
                .lastname("Castell")
                .email("jcastellb@skype.com")
                .age("14")
                .address("0 Spaight Circle")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user7 = User.builder()
                .username("pcotterellw")
                .password(encoder.encode("123"))
                .firstname("Putnem")
                .lastname("Cotterell")
                .email("pcotterellw@symantec.com")
                .age("45")
                .address("7 Scofield Park")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user8 = User.builder()
                .username("nmaclucais22")
                .password(encoder.encode("123"))
                .firstname("Ninnette")
                .lastname("MacLucais")
                .email("nmaclucais22@icio.us")
                .age("8")
                .address("432 Dottie Alley")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user9 = User.builder()
                .username("aboost2g")
                .password(encoder.encode("123"))
                .firstname("Ame")
                .lastname("Boost")
                .email("aboost2g@hibu.com")
                .age("25")
                .address("35 Marquette Center")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user10 = User.builder()
                .username("bro")
                .password(encoder.encode("123"))
                .firstname("Ban")
                .lastname("Grosier")
                .email("bgrosier2w@wikispaces.com")
                .age("20")
                .address("6 Golf Course Terrace")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user11 = User.builder()
                .username("sondrak4a")
                .password(encoder.encode("123"))
                .firstname("Stephie")
                .lastname("Ondrak")
                .email("sondrak4a@ocn.ne.jp")
                .age("26")
                .address("30 Mockingbird Junction")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user12 = User.builder()
                .username("bpinnick5e")
                .password(encoder.encode("123"))
                .firstname("Barron")
                .lastname("Pinnick")
                .email("bpinnick5e@discuz.net")
                .age("11")
                .address("487 Sunnyside Court")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user13 = User.builder()
                .username("TokiDokor")
                .password(encoder.encode("TokiDokor"))
                .firstname("Suradit")
                .lastname("Luo")
                .email("gialeng@outlook.com")
                .age("20")
                .address("Chiang Rai")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user14 = User.builder()
                .username("A1iBa")
                .password(encoder.encode("A1iBa"))
                .firstname("Jack")
                .lastname("Ma")
                .email("ali@baba.com")
                .age("56")
                .address("Beijing")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user15 = User.builder()
                .username("BatDad")
                .password(encoder.encode("BatDad"))
                .firstname("Thomas")
                .lastname("Wayne")
                .email("wayne@gotham.com")
                .age("40")
                .address("Gotham")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user16 = User.builder()
                .username("MethMan")
                .password(encoder.encode("MethMan"))
                .firstname("Walter")
                .lastname("White")
                .email("breaking@bad.com")
                .age("46")
                .address("USA")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        user17 = User.builder()
                .username("MethManWife")
                .password(encoder.encode("MethManWife"))
                .firstname("Skyler")
                .lastname("White")
                .email("breaking@bad.com")
                .age("42")
                .address("USA")
                .enabled(true)
                .lastPasswordResetDate(Date.from(LocalDate.of(2022, 10, 30)
                        .atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .build();
        authorityRepository.save(authUser);
        authorityRepository.save(authAdmin);
        authorityRepository.save(authDoctor);
        authorityRepository.save(authPatient);
        user1.getAuthorities().add(authUser);
        user1.getAuthorities().add(authAdmin);
        user2.getAuthorities().add(authUser);
        user3.getAuthorities().add(authUser);
        user3.getAuthorities().add(authDoctor);
        user4.getAuthorities().add(authUser);
        user4.getAuthorities().add(authPatient);
        user5.getAuthorities().add(authUser);
        user5.getAuthorities().add(authDoctor);
        user6.getAuthorities().add(authUser);
        user6.getAuthorities().add(authPatient);
        user7.getAuthorities().add(authUser);
        user7.getAuthorities().add(authPatient);
        user8.getAuthorities().add(authUser);
        user8.getAuthorities().add(authPatient);
        user9.getAuthorities().add(authUser);
        user9.getAuthorities().add(authPatient);
        user10.getAuthorities().add(authUser);
        user10.getAuthorities().add(authPatient);
        user11.getAuthorities().add(authUser);
        user11.getAuthorities().add(authPatient);
        user12.getAuthorities().add(authUser);
        user12.getAuthorities().add(authPatient);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
        userRepository.save(user5);
        userRepository.save(user6);
        userRepository.save(user7);
        userRepository.save(user8);
        userRepository.save(user9);
        userRepository.save(user10);
        userRepository.save(user11);
        userRepository.save(user12);
    }
}
