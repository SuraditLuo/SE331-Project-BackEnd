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
    public Patient saveDoctor(Patient patient) {
        Patient p = patientDao.findById(patient.getId()).orElse(null);
        Doctor doctor = doctorDao.findById(patient.getDoctor().getId()).orElse(null);
        p.setDoctor(doctor);
        doctor.getInCharge().add(p);
        return patientDao.save(p);
    }

    @Override
    @Transactional
    public Patient saveVaccine(Patient patient) {
        Patient p = patientDao.findById(patient.getId()).orElse(null);
        return patientDao.save(p);
    }

    @Override
    public Page<Patient> getPatients(String name, Pageable pageable) {
        return patientDao.getPatients(name,pageable);
    }
}
