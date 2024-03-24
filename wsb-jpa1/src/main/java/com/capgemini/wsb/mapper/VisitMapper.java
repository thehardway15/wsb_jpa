package com.capgemini.wsb.mapper;

import com.capgemini.wsb.dto.MedicalTreatmentTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.Collection;
import java.util.Collections;
import java.util.stream.Collectors;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity visitEntity)
    {
        if (visitEntity == null)
        {
            return null;
        }
        final VisitTO visitTO = new VisitTO();
        visitTO.setId(visitEntity.getId());
        visitTO.setDescription(visitEntity.getDescription());
        visitTO.setTime(visitEntity.getTime());

        Collection<MedicalTreatmentTO> medicalTreatments = Collections.emptyList();
        if (visitEntity.getMedicalTreatments() != null)
        {
            medicalTreatments = visitEntity.getMedicalTreatments().stream()
                    .map(MedicalTreatmentMapper::mapToTO)
                    .collect(Collectors.toList());
        }

        visitTO.setMedicalTreatments(medicalTreatments);
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO)
    {
        if(visitTO == null)
        {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setId(visitTO.getId());
        visitEntity.setDescription(visitTO.getDescription());
        visitEntity.setTime(visitTO.getTime());

        return visitEntity;
    }


}
