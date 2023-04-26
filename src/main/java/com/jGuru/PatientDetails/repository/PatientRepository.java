package com.jGuru.PatientDetails.repository;

import com.jGuru.PatientDetails.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * This interface is connecting to the database to do the CURD operations on Patient entity
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
 *      This interface acts as persistence layer for Patient entity
 * </p>
 * <p>
 *     This interface extends the JpaRepository
 * </p>z
 *
 * @author      Umesh M
 * @version     1.0
 * @since       1.0
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    public List<Patient> findByFirstNameContainingIgnoreCase(String firstName);
}
