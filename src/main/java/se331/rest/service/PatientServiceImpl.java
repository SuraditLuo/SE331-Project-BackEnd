package se331.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import se331.rest.DAO.DoctorDao;
import se331.rest.DAO.PatientDao;
import se331.rest.DAO.VaccineDao;
import se331.rest.entity.Doctor;
import se331.rest.entity.Patient;
import se331.rest.entity.Vaccine;

import javax.transaction.Transactional;

@Service
public class PatientServiceImpl implements PatientService{
    @Autowired
    PatientDao patientDao;

    @Autowired
    DoctorDao doctorDao;

    @Autowired
    VaccineDao vaccineDao;

    @Override
    public Integer getPatientSize() {
        return patientDao.getPatientSize();
    }

    @Override
    public Page<Patient> getPatients(Integer pageSize, Integer page) {
        return patientDao.getPatients(pageSize, page);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientDao.getPatients(id);
    }

    @Override
    @Transactional
    public Patient save(Patient patient) {
        Doctor doctor = doctorDao.findById(patient.getDoctor().getId()).orElse(null);
        Vaccine vaccine1 = vaccineDao.findById(patient.getListOfDose().get(0).getId()).orElse(null);
        Vaccine vaccine2 = vaccineDao.findById(patient.getListOfDose().get(1).getId()).orElse(null);
        patient.setDoctor(doctor);
        patient.getListOfDose().add(vaccine1);
        patient.getListOfDose().add(vaccine2);
        doctor.getInCharge().add(patient);
        vaccine1.getPatients().add(patient);
        vaccine2.getPatients().add(patient);

        return patientDao.save(patient);
    }

    @Override
    public Page<Patient> getPatients(String name, Pageable pageable) {
        return patientDao.getPatients(name,pageable);
    }
}
