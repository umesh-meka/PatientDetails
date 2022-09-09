package com.jGuru.PatientDetails.controller;

import com.jGuru.PatientDetails.entity.Patient;
import com.jGuru.PatientDetails.exceptions.PatientDataNotFound;
import com.jGuru.PatientDetails.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * This class is used to handle the all incoming requests for doing CURD operations on Patient class object
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
 * @author      Umesh M
 * @version     1.0
 * @since       1.0
 */
@RestController
@RequestMapping("patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * This is used to save the patient data in database
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with unique record id after saving the patient in databse
     */
    @PostMapping("/save")
    public Patient save(@Valid @RequestBody Patient patient) {
        return  patientService.save(patient);
    }

    /**
     * This method is used fetch the particular patient record from database else throws exception
     * @param id vatiable contains the unique id of patient record
     * @return patient object will be returned whose record is matching with the given id
     */
    @GetMapping("/findById/{id}")
    public  Patient findById(@PathVariable Long id) throws PatientDataNotFound {
        return patientService.findById(id);
    }

    /**
     * This method is used fetch all patient record from database which are having the given name as first name
     * This method is used fetch all patient record from database
     * @return list of all patients who are all in the database
     */
    @GetMapping("/findAll")
    public List<Patient> findAllPatients() {
        return patientService.findAllPatients();
    }

    /**
     * This method is used fetch all patient record from database which are having the given name as first name
     * @param firstName variable holding the patient name
     * @return list of patients who are all matching with the given pName
     */
    @GetMapping("findByName/{firstName}")
    public List<Patient> findAllPatientsByName(@PathVariable String firstName) {
        return patientService.findAllPatientsByName(firstName);
    }

    /**
     * This method is used fetch the particular patient record from database and update it with given details else throws exception
     * @param id vatiable contains the unique id of patient record
     * @param patient variable contains the JSON format of patient object from HTTP request
     * @return new patient object with updated details from patient object in HTTP request
     */
    @PutMapping("update/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Patient> updatePatient(@PathVariable("id") Long id,
                                                 @RequestBody Patient patient) throws PatientDataNotFound {
        //return patientService.updatePatient(id, patient);
        return ResponseEntity.ok().body(patientService.updatePatient(id, patient));
    }

    /**
     * This is used to delete the particular patient from database
     * @param id vatiable contains the unique id of patient record
     * @return either success message if patient record is available or failure message if no patient record is available with given id
     */
    @DeleteMapping("/delete/{id}")
    public String deletePatient(@PathVariable Long id) throws PatientDataNotFound {
        return patientService.deletePatient(id);
    }
}
