package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;

import java.util.Collection;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long> {

    List<PatientEntity> findByLastName(String lastName);

    List<PatientEntity> findByDayOffGreaterThan(Long dayOff);

    List<PatientEntity> findByVisitsCountGreaterThan(Long visitsCount);

    List<VisitEntity> findVisitsByPatientId(Long id);
}
