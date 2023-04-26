package com.jGuru.PatientDetails.entity;

import lombok.*;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class Address {

    public enum AddressType { PERMANENT, CURRENT};

    @Id
    @SequenceGenerator(
            name = "address_sequence",
            sequenceName = "address_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "address_sequence"
    )
    private Long id;
    private String streetName;
    private String cityName;
    private String stateName;
    private Long pinCode;

    @Enumerated(EnumType.STRING)
    private AddressType addressType;
}
