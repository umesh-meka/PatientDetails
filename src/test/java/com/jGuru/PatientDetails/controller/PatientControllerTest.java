package com.jGuru.PatientDetails.controller;

import com.jGuru.PatientDetails.entity.Address;
import com.jGuru.PatientDetails.entity.Patient;
import com.jGuru.PatientDetails.repository.PatientRepository;
import com.jGuru.PatientDetails.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PatientController.class)
class PatientControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PatientService patientService;

    @MockBean
    private PatientRepository patientRepository;

    private Address address;
    private Patient patient;

    List<Address> addressList  = new ArrayList<Address>();

    @BeforeEach
    void setUp() {
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
                .firstName("Umesh")
                .lastName("Meka")
                .dob(new Date(2001-01-01))
                .gender(Patient.Gender.MALE)
                .address(addressList)
                .telNumber(1234567890L)
                .build();
    }

    // Controller Layer JUnit test for save method
    @DisplayName("Controller Layer JUnit test for save method")
    @Test
    public void givenPatientObject_whenSavePatient_thenReturnPatientObject() throws Exception {
        Address address = Address.builder()
                .streetName("street2")
                .cityName("city2")
                .stateName("state2")
                .pinCode(123456L)
                .build();

        List<Address> addressList1 = new ArrayList<Address>();
        addressList1.add(address);

        Patient inputPatient = Patient.builder()
                .firstName("Umesh")
                .lastName("Meka")
                .dob(new Date(2002-02-02))
                .gender(Patient.Gender.MALE)
                .address(addressList)
                .telNumber(1234567890L)
                .build();

        Mockito.when(patientService.save(inputPatient))
                .thenReturn(patient);

        mockMvc.perform(MockMvcRequestBuilders.post("/patient/save")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\" : \"Umesh\",\n" +
                        "    \"lastName\" : \"Meka\",\n" +
                        "    \"dob\" : \"2002-02-02\",\n" +
                        "    \"gender\" : \"MALE\",\n" +
                        "    \"address\" : [{        \n" +
                        "    \"streetName\" : \"street2\",\n" +
                        "    \"cityName\" : \"city2\",\n" +
                        "    \"stateName\" : \"sate2\",\n" +
                        "    \"pinCode\" : 12345\n" +
                        "    }],\n" +
                        "    \"telNumber\" : 1234567890\n" +
                        "}")).andExpect(status().isOk());

    }

    // Controller Layer JUnit test for findAllPatients method
    @DisplayName("Controller Layer JUnit test for findAllPatients method")
    @Test
    public void givenPatientsList_whenGetAllPatients_thenReturnPatientsList() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        Mockito.when(patientRepository.findAll()).thenReturn(patients);
        Mockito.when(patientService.findAllPatients()).thenReturn(patients);

        MvcResult requestResult = this.mockMvc.perform(get("/patient/findAll/")).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertNotNull(result);
    }

    // Controller Layer JUnit test for findById method
    @DisplayName("Controller Layer JUnit test for findById method")
    @Test
    public void givenPatientId_whenGetPatientById_thenReturnPatientObject() throws Exception {
        Mockito.when(patientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(patient));
        Mockito.when(patientService.findById(Mockito.anyLong())).thenReturn(patient);

        MvcResult requestResult = this.mockMvc.perform(get("/patient/findById/19")).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertNotNull(result);
    }

    // Controller Layer JUnit test for findAllPatientsByName method
    @DisplayName("Controller Layer JUnit test for findAllPatientsByName method")
    @Test
    public void givenPatientName_whenGetPatientsByName_thenReturnPatientsList() throws Exception {
        List<Patient> patients = new ArrayList<>();
        patients.add(patient);
        Mockito.when(patientRepository.findByFirstName(patient.getFirstName())).thenReturn(patients);
        Mockito.when(patientService.findAllPatientsByName(patient.getFirstName())).thenReturn(patients);

        MvcResult requestResult = this.mockMvc.perform(get("/patient/findByName/{name}",patient.getFirstName())).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertNotNull(result);
    }

    // Controller Layer JUnit test for updatePatient method
    @DisplayName("Controller Layer JUnit test for updatePatient method")
    @Test
    public void givenPatientObject_whenUpdatePatient_thenReturnUpdatedPatient() throws Exception {
        Mockito.when(patientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(patient));
        Mockito.when(patientService.updatePatient(Mockito.anyLong(), Mockito.any(Patient.class))).thenReturn(patient);

        MvcResult requestResult = mockMvc.perform(put("/patient/update/19").header("Content-Type", MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "    \"firstName\" : \"Umesh\",\n" +
                        "    \"lastName\" : \"Meka\",\n" +
                        "    \"dob\" : \"2002-02-02\",\n" +
                        "    \"gender\" : \"MALE\",\n" +
                        "    \"address\" : [{        \n" +
                        "    \"streetName\" : \"street2\",\n" +
                        "    \"cityName\" : \"city2\",\n" +
                        "    \"stateName\" : \"sate2\",\n" +
                        "    \"pinCode\" : 12345\n" +
                        "    }],\n" +
                        "    \"telNumber\" : 1234567890\n" +
                        "}")).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();

        String result = requestResult.getResponse().getContentAsString();
        assertNotNull(result);
    }

    // Controller Layer JUnit test for deletePatient method
    @DisplayName("Controller Layer JUnit test for deletePatient method")
    @Test
    public void givenPatientId_whenDeletePatient_thenNothing() throws Exception {
        Mockito.when(patientRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(patient));

        MvcResult requestResult = this.mockMvc.perform(delete("/patient/delete/1")).andExpect(status().isOk()).andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertNotNull(result);
    }
}