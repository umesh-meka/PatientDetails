package com.jGuru.PatientDetails.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Patient {

    public enum Gender { MALE, FEMALE};

    @Id
    @SequenceGenerator(
            name = "patient_sequence",
            sequenceName = "patient_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long ID;

    @NotBlank(message = "FirstName is mandatory")
    private String firstName;

    @NotBlank(message = "LastName is mandatory")
    private String lastName;

    @Temporal(TemporalType.DATE)
    @Past(message = "Enter valid date.")
    private Date dob;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn( name = "patient_id", referencedColumnName = "ID", nullable = false,  updatable = false)
    private List<Address> address;

    @Digits(message="Number should contain 10 digits.", fraction = 0, integer = 10)
    private Long telNumber;
}
