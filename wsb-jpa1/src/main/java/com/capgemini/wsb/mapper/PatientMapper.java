package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.PatientShortTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.PatientEntity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public final class PatientMapper {

    public static PatientTO mapToTO(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }
        final PatientTO patientTO = new PatientTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setTelephoneNumber(patientEntity.getTelephoneNumber());
        patientTO.setEmail(patientEntity.getEmail());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setDateOfBirth(patientEntity.getDateOfBirth());
        patientTO.setVerified(patientEntity.isVerified());
        patientTO.setDayOff(patientEntity.getDayOff());

        final Collection<VisitTO> visits = new ArrayList<>(Collections.emptyList());
        if (patientEntity.getVisits() != null)
        {
            visits.addAll(patientEntity.getVisits().stream()
                    .map(VisitMapper::mapToTO)
                    .collect(Collectors.toList()));
        }
        patientTO.setVisits(visits);

        return patientTO;
    }


    public static PatientShortTO mapToTOShort(final PatientEntity patientEntity)
    {
        if (patientEntity == null)
        {
            return null;
        }
        final PatientShortTO patientTO = new PatientShortTO();
        patientTO.setId(patientEntity.getId());
        patientTO.setFirstName(patientEntity.getFirstName());
        patientTO.setLastName(patientEntity.getLastName());
        patientTO.setPatientNumber(patientEntity.getPatientNumber());
        patientTO.setVerified(patientEntity.isVerified());

        return patientTO;
    }

    public static PatientEntity mapToEntity(final PatientTO patientTO)
    {
        if (patientTO == null)
        {
            return null;
        }
        final PatientEntity patientEntity = new PatientEntity();
        patientEntity.setId(patientTO.getId());
        patientEntity.setFirstName(patientTO.getFirstName());
        patientEntity.setLastName(patientTO.getLastName());
        patientEntity.setTelephoneNumber(patientTO.getTelephoneNumber());
        patientEntity.setEmail(patientTO.getEmail());
        patientEntity.setPatientNumber(patientTO.getPatientNumber());
        patientEntity.setDateOfBirth(patientTO.getDateOfBirth());
        patientEntity.setVerified(patientTO.isVerified());

        return patientEntity;
    }

}
