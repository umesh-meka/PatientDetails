package com.jGuru.PatientDetails.repository;

import com.jGuru.PatientDetails.entity.Address;
import com.jGuru.PatientDetails.entity.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
class PatientRepositoryTest {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    private Patient patient;
    private Address address;

    List<Address> addressList  = new ArrayList<Address>();

    @BeforeEach
    void setUp() {

        address = Address.builder()
                .streetName("street1RepoTest")
                .cityName("city1RepoTest")
                .stateName("state1RepoTest")
                .pinCode(123456L)
                .build();

        addressList.add(address);

        //testEntityManager.persist(address);

        patient = Patient.builder()
                .firstName("Patient1RepoTest")
                .lastName("Patient1RepoTest")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList)
                .telNumber(1234567890L)
                .build();

        testEntityManager.persist(patient);
    }

    // Repository Layer JUnit test for save method
    @DisplayName("Repository Layer JUnit test for save method")
    @Test
    public void givenPatientObject_whenSavePatient_thenReturnPatientObject() {
        address = Address.builder()
                .streetName("street1RepoTest")
                .cityName("city1RepoTest")
                .stateName("state1RepoTest")
                .pinCode(123456L)
                .build();
        addressList.add(address);
        patient = Patient.builder()
                .firstName("Patient1RepoTest")
                .lastName("Patient1RepoTest")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList)
                .telNumber(1234567890L)
                .build();

        Patient patientActual = patientRepository.save(patient);

        assertThat(patientActual).isNotNull();
        assertThat(patientActual.getID()).isGreaterThan(0);
    }

    // Repository Layer JUnit test for findById method
    @DisplayName("Repository Layer JUnit test for findById method")
    @Test
    public void givenPatientId_whenGetPatientById_thenReturnPatientObject() {
        Patient patientActual = patientRepository.findById(patient.getID()).get();
        assertEquals("Patient1RepoTest", patientActual.getFirstName());
    }

    // Repository Layer JUnit test for findAllPatientsByName method
    @DisplayName("Repository Layer JUnit test for findAllPatientsByName method")
    @Test
    public void givenPatientName_whenGetPatientsByName_thenReturnPatientsList() {

        List<Patient> patientList = patientRepository.findByFirstNameContainingIgnoreCase("Patient1RepoTest");

        assertEquals(1, patientList.size());
    }

   // Repository Layer JUnit test for findAllPatients method
    @DisplayName("Repository Layer JUnit test for findAllPatients method")
    @Test
    public void givenPatientsList_whenGetAllPatients_thenReturnPatientsList() {

        int expectedSize = patientRepository.findAll().size();

        Address address = Address.builder()
                .streetName("street2RepoTest")
                .cityName("city2RepoTest")
                .stateName("state2RepoTest")
                .pinCode(123456L)
                .build();

        List<Address> addressList1 = new ArrayList<Address>();
        addressList1.add(address);

        Patient patient1 = Patient.builder()
                .firstName("Patient2RepoTest")
                .lastName("Patient2RepoTest")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList1)
                .telNumber(1234567890L)
                .build();

        patientRepository.save(patient1);
        List<Patient> patientList = patientRepository.findAll();

        assertEquals(expectedSize+1, patientList.size());
    }

    // Repository Layer JUnit test for updatePatient method
    @DisplayName("Repository Layer JUnit test for updatePatient method")
    @Test
    public void givenPatientObject_whenUpdatePatient_thenReturnUpdatedPatient() {

        Patient patientSaved = patientRepository.findById(patient.getID()).get();

        patientSaved.setFirstName("Patient1RepoTestUpdated");
        Patient patientUpdated = patientRepository.findById(patientSaved.getID()).get();

        assertEquals("Patient1RepoTestUpdated", patientUpdated.getFirstName());
    }

    // Repository Layer JUnit test for deletePatient method
    @DisplayName("Repository Layer JUnit test for deletePatient method")
    @Test
    public void givenPatientId_whenDeletePatient_thenNothing() {
        patientRepository.deleteById(patient.getID());

        Optional<Patient> optionalPatient = patientRepository.findById(patient.getID());

        assertEquals(true, !optionalPatient.isPresent());
    }
}