package com.jGuru.PatientDetails.service;

import com.jGuru.PatientDetails.entity.Address;
import com.jGuru.PatientDetails.entity.Patient;
import com.jGuru.PatientDetails.exceptions.PatientDataNotFound;
import com.jGuru.PatientDetails.repository.PatientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {
    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientServiceImpl patientServiceImpl;

    private Address address;
    private Patient patient;

    List<Address> addressList  = new ArrayList<Address>();

    @BeforeEach
    public void setup(){
        address = Address.builder()
                .id(20L)
                .streetName("street1")
                .cityName("city1")
                .stateName("state1")
                .pinCode(123456L)
                .build();

        addressList.add(address);

        patient = Patient.builder()
                .ID(19L)
                .firstName("Patient1Test")
                .lastName("Patient1Test")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList)
                .telNumber(1234567890L)
                .build();
    }

    // Service Layer JUnit test for save method
    @DisplayName("Service Layer JUnit test for save method")
    @Test
    public void givenPatientObject_whenSavePatient_thenReturnPatientObject(){
        // given - precondition or setup
        given(patientRepository.save(patient)).willReturn(patient);

        // when -  action or the behaviour that we are going test
        Patient savedPatient = patientServiceImpl.save(patient);

        // then - verify the output
        assertThat(savedPatient).isNotNull();
    }

    // Service Layer JUnit test for findAllPatients method
    @DisplayName("Service Layer JUnit test for findAllPatients method")
    @Test
    public void givenPatientsList_whenGetAllPatients_thenReturnPatientsList(){
        // given - precondition or setup
        Address address = Address.builder()
                .id(22L)
                .streetName("street2")
                .cityName("city1")
                .stateName("state1")
                .pinCode(123456L)
                .build();

        List<Address> addressList1  = new ArrayList<Address>();
        addressList1.add(address);

        Patient patient1 = Patient.builder()
                .ID(21L)
                .firstName("Patient2Test")
                .lastName("Patient2Test")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList1)
                .telNumber(1234567890L)
                .build();

        given(patientRepository.findAll()).willReturn(List.of(patient,patient1));

        // when -  action or the behaviour that we are going test
        List<Patient> patientList = patientServiceImpl.findAllPatients();

        // then - verify the output
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isEqualTo(2);
    }

    // Service Layer JUnit test for findAllPatients method
    @DisplayName("Service Layer JUnit test for findAllPatients method (negative scenario)")
    @Test
    public void givenEmptyPatientsList_whenGetAllPatients_thenReturnEmptyPatientsList(){
        // given - precondition or setup
        Address address = Address.builder()
                .id(22L)
                .streetName("street2")
                .cityName("city1")
                .stateName("state1")
                .pinCode(123456L)
                .build();

        List<Address> addressList1  = new ArrayList<Address>();
        addressList1.add(address);

        Patient patient1 = Patient.builder()
                .ID(21L)
                .firstName("Patient2Test")
                .lastName("Patient2Test")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList1)
                .telNumber(1234567890L)
                .build();

        given(patientRepository.findAll()).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Patient> patientList = patientServiceImpl.findAllPatients();

        // then - verify the output
        assertThat(patientList).isEmpty();
        assertThat(patientList.size()).isEqualTo(0);
    }

    // Service Layer JUnit test for findById method
    @DisplayName("Service Layer JUnit test for findById method")
    @Test
    public void givenPatientId_whenGetPatientById_thenReturnPatientObject() throws PatientDataNotFound {
        // given
        given(patientRepository.findById(19L)).willReturn(Optional.of(patient));

        // when
        Patient savedPatient = patientServiceImpl.findById(patient.getID());

        // then
        assertThat(savedPatient).isNotNull();
    }

    // Service Layer JUnit test for findById method
    @DisplayName("Service Layer JUnit test for findById method (negative scenario)")
    @Test
    public void givenPatientId_whenGetPatientById_thenThrowExceptionIfIdNotFound() throws PatientDataNotFound {
        // given - precondition or setup
        given(patientRepository.findById(19L)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        Executable executable = () ->  patientServiceImpl.findById(19L);

        // then - verify the output
        assertThrows(PatientDataNotFound.class, executable);
    }

    // Service Layer JUnit test for findAllPatientsByName method
    @DisplayName("Service Layer JUnit test for findAllPatientsByName method")
    @Test
    public void givenPatientName_whenGetPatientsByName_thenReturnPatientsList() {
        // given
        String patientName = "Patient1Test";
        Address address = Address.builder()
                .id(22L)
                .streetName("street2")
                .cityName("city1")
                .stateName("state1")
                .pinCode(123456L)
                .build();

        List<Address> addressList1  = new ArrayList<Address>();
        addressList1.add(address);

        Patient patient1 = Patient.builder()
                .ID(21L)
                .firstName("Patient1Test")
                .lastName("Patient1Test")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList1)
                .telNumber(1234567890L)
                .build();

        given(patientRepository.findByFirstName(patientName)).willReturn(List.of(patient,patient1));

        // when -  action or the behaviour that we are going test
        List<Patient> patientList = patientServiceImpl.findAllPatientsByName(patientName);

        // then - verify the output
        assertThat(patientList).isNotNull();
        assertThat(patientList.size()).isEqualTo(2);
    }

    // Service Layer JUnit test for findAllPatientsByName method
    @DisplayName("Service Layer JUnit test for findAllPatientsByName method (negative scenario)")
    @Test
    public void givenEmptyPatientsList_whenGetAllPatientsByName_thenReturnEmptyPatientsList(){
        // given - precondition or setup
        String patientName = "NoName";
        given(patientRepository.findByFirstName(patientName)).willReturn(Collections.emptyList());

        // when -  action or the behaviour that we are going test
        List<Patient> patientList = patientServiceImpl.findAllPatientsByName(patientName);

        // then - verify the output
        assertThat(patientList).isEmpty();
        assertThat(patientList.size()).isEqualTo(0);
    }

    // Service Layer JUnit test for updatePatient method
    @DisplayName("Service Layer JUnit test for updatePatient method")
    @Test
    public void givenPatientObject_whenUpdatePatient_thenReturnUpdatedPatient() throws PatientDataNotFound {
        // given - precondition or setup
        given(patientRepository.findById(19L)).willReturn(Optional.of(patient));
        given(patientRepository.save(patient)).willReturn(patient);

        patient.setFirstName("Patient3Test");
        patient.setLastName("Patient3Test");
        patient.setDob(new Date(2002-02-02));
        patient.setTelNumber(1234567890L);
        patient.setGender(Patient.Gender.FEMALE);

        List<Address> address = patient.getAddress();
        address.get(0).setStreetName("test2");
        address.get(0).setCityName("test2");
        address.get(0).setStateName("test2");
        address.get(0).setPinCode(12345L);
        address.get(0).setId(24L);
        patient.setAddress(address);

        // when -  action or the behaviour that we are going test
        Patient updatedPatient = patientServiceImpl.updatePatient(19L, patient);

        // then - verify the output
        assertThat(updatedPatient.getFirstName()).isEqualTo("Patient3Test");
        assertThat(updatedPatient.getLastName()).isEqualTo("Patient3Test");
        assertThat(updatedPatient.getDob()).isEqualTo(new Date(2002-02-02));
        assertThat(updatedPatient.getTelNumber()).isEqualTo(1234567890L);
        assertThat(updatedPatient.getGender()).isEqualTo(Patient.Gender.FEMALE);
        assertThat(updatedPatient.getAddress().get(0).getStreetName()).isEqualTo("test2");
        assertThat(updatedPatient.getAddress().get(0).getCityName()).isEqualTo("test2");
        assertThat(updatedPatient.getAddress().get(0).getStateName()).isEqualTo("test2");
        assertThat(updatedPatient.getAddress().get(0).getPinCode()).isEqualTo(12345L);
        assertThat(updatedPatient.getAddress().get(0).getId()).isEqualTo(24L);
    }

    // Service Layer JUnit test for updatePatient method
    @DisplayName("Service Layer JUnit test for updatePatient method (negative scenario)")
    @Test
    public void givenPatientObject_whenUpdatePatient_thenThrowExceptionIfIdNotFound() throws PatientDataNotFound {
        // given - precondition or setup
        given(patientRepository.findById(100L)).willReturn(Optional.empty());


        // when -  action or the behaviour that we are going test
        Executable executable = () ->  patientServiceImpl.updatePatient(100L, patient);

        // then - verify the output
        assertThrows(PatientDataNotFound.class, executable);
    }


    // Service Layer JUnit test for deletePatient method
    @DisplayName("Service Layer JUnit test for deletePatient method")
    @Test
    public void givenPatientId_whenDeletePatient_thenNothing() throws PatientDataNotFound {
        // given - precondition or setup
        long patientId = 19L;
        given(patientRepository.findById(patientId)).willReturn(Optional.of(patient));

        willDoNothing().given(patientRepository).deleteById(patientId);

        // when -  action or the behaviour that we are going test
        patientServiceImpl.deletePatient(patientId);

        // then - verify the output
        verify(patientRepository, times(1)).deleteById(patientId);
    }

    // Service Layer JUnit test for deletePatient method
    @DisplayName("Service Layer JUnit test for deletePatient method (negative scenario)")
    @Test
    public void givenPatientId_whenDeletePatient_thenThrowExceptionIfIdNotFound() throws PatientDataNotFound {
        // given - precondition or setup
        long patientId = 100L;
        given(patientRepository.findById(patientId)).willReturn(Optional.empty());

        // when -  action or the behaviour that we are going test
        Executable executable = () ->  patientServiceImpl.deletePatient(patientId);

        // then - verify the output
        assertThrows(PatientDataNotFound.class, executable);
    }
}