package com.capgemini.wsb.persistence.dao.impl;

import com.capgemini.wsb.persistence.dao.PatientDao;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao {


    @Override
    public List<PatientEntity> findByLastName(String lastName) {
        return entityManager.createQuery("select p from PatientEntity p where p.lastName like :lastName", PatientEntity.class)
                .setParameter("lastName", "%" + lastName + "%")
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByDayOffGreaterThan(Long dayOff) {
        return entityManager.createQuery("select p from PatientEntity p where p.dayOff > :dayOff", PatientEntity.class)
                .setParameter("dayOff", dayOff)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findByVisitsCountGreaterThan(Long visitsCount) {
        return entityManager.createQuery("select distinct p from PatientEntity p " +
                        " join p.visits v" +
                        " group by p having count(v) > :visitsCount", PatientEntity.class)
                .setParameter("visitsCount", visitsCount)
                .getResultList();
    }

    @Override
    public List<VisitEntity> findVisitsByPatientId(Long id) {
        return entityManager.createQuery("select v from VisitEntity v where v.patient.id = :id", VisitEntity.class)
                .setParameter("id", id)
                .getResultList();
    }


}
