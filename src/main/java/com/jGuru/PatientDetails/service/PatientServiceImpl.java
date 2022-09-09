package com.jGuru.PatientDetails.service;

import com.jGuru.PatientDetails.entity.Patient;
import com.jGuru.PatientDetails.exceptions.PatientDataNotFound;
import com.jGuru.PatientDetails.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * This class contains the actual business logic for CURD operations on Patient class object
 * This class will handle the below CURD operations:
 * <ul>
 * <li> Patient data saving
 * <li> Patient data updating
 * <li> Patient data deleting by ID
 * <li> Finding Patient by ID
 * <li> Finding Patient by patient name
 * <li> Finding all Patients list in database
 * </ul>
 *
 * <p>
 *     This class implements the PatientService
 * </p>
 *
 * @author      Umesh M
 * @version     1.0
 * @since       1.0
 */
@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    /**
     * This is used to save the patient data in database
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with unique record id after saving the patient in databse
     */
    @Override
    public Patient save(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * This method is used fetch the particular patient record from database else throws exception
     * @param id vatiable contains the unique id of patient record
     * @return patient object will be returned whose record is matching with the given id
     */
    @Override
    public Patient findById(Long id) throws PatientDataNotFound {
        Optional<Patient> optional = patientRepository.findById(id);
        if(!optional.isPresent())
            throw new PatientDataNotFound("Patient data not found with provided ID");
        return optional.get();
    }

    /**
     * This method is used fetch all patient record from database
     * @return list of all patients who are all in the database
     */
    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * This method is used fetch all patient record from database which are having the given name as first name
     * @param firstName variable holding the patient name
     * @return list of patients who are all matching with the given firstName
     */
    @Override
    public List<Patient> findAllPatientsByName(String firstName) {
        return patientRepository.findByFirstName(firstName);
    }

    /**
     * This method is used fetch the particular patient record from database and update it with given details else throws exception
     * @param id vatiable contains the unique id of patient record
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with updated details from patient object in HTTP request
     */
    @Override
    public Patient updatePatient(Long id, Patient patient) throws PatientDataNotFound {
        Optional<Patient> optional = patientRepository.findById(id);
        if(!optional.isPresent())
            throw new PatientDataNotFound("Patient data not found with provided ID");

        Patient patient1 = optional.get();
        if(Objects.nonNull(patient.getFirstName()) && !"".equalsIgnoreCase(patient.getFirstName()))
            patient1.setFirstName(patient.getFirstName());
        if(Objects.nonNull(patient.getLastName()) && !"".equalsIgnoreCase(patient.getLastName()))
            patient1.setLastName(patient.getLastName());
        if(Objects.nonNull(patient.getDob()))
            patient1.setDob(patient.getDob());
        if(Objects.nonNull(patient.getAddress()))
            patient1.setAddress(patient.getAddress());
        if(Objects.nonNull(patient.getGender()))
            patient1.setGender(patient.getGender());
        if(Objects.nonNull(patient.getTelNumber()))
            patient1.setTelNumber(patient.getTelNumber());

        return patientRepository.save(patient1);
    }

    /**
     * This is used to delete the particular patient from database
     * @param id vatiable contains the unique id of patient record
     * @return either success message if patient record is available or failure message if no patient record is available with given id
     */
    @Override
    public String deletePatient(Long id) throws PatientDataNotFound {
        if(!patientRepository.findById(id).isPresent())
                throw new PatientDataNotFound("Patient data not found with provided ID");
            patientRepository.deleteById(id);
            return "Patient data deleted successfully";
    }
}