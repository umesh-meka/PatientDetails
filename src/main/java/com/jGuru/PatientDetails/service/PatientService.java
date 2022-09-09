package com.jGuru.PatientDetails.service;

import com.jGuru.PatientDetails.entity.Patient;
import com.jGuru.PatientDetails.exceptions.PatientDataNotFound;

import java.util.List;

/**
 * This interface is used for CURD operations on Patient class object
 * This interface will handle the below CURD operations:
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
 *     PatientServiceImpl class implements the PatientService
 * </p>
 *
 * @author      Umesh M
 * @version     1.0
 * @since       1.0
 */
public interface PatientService {

    /**
     * This is used to save the patient data in database
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with unique record id after saving the patient in databse
     */
    public Patient save(Patient patient);

    /**
     * This method is used fetch the particular patient record from database else throws exception
     * @param id vatiable contains the unique id of patient record
     * @return patient object will be returned whose record is matching with the given id
     */
    public Patient findById(Long id) throws PatientDataNotFound;

    /**
     * This method is used fetch all patient record from database
     * @return list of all patients who are all in the database
     */
    public List<Patient> findAllPatients();

    /**
     * This method is used fetch all patient record from database which are having the given name as first name
     * @param pName variable holding the patient name
     * @return list of patients who are all matching with the given pName
     */
    public List<Patient> findAllPatientsByName(String pName);

    /**
     * This method is used fetch the particular patient record from database and update it with given details else throws exception
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with updated details from patient object in HTTP request
     */
    public Patient updatePatient(Long id, Patient patient) throws PatientDataNotFound;

    /**
     * This is used to delete the particular patient from database
     * @param id vatiable contains the unique id of patient record
     * @return either success message if patient record is available or failure message if no patient record is available with given id
     */
    public String deletePatient(Long id) throws PatientDataNotFound;
}
