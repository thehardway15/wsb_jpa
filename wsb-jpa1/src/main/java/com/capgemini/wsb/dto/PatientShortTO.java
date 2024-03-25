package com.capgemini.wsb.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

public class PatientShortTO implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String patientNumber;

    private Boolean verified;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getPatientNumber() {
        return patientNumber;
    }

    public void setPatientNumber(String patientNumber) {
        this.patientNumber = patientNumber;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public Boolean isVerified() {
        return verified;
    }

}
