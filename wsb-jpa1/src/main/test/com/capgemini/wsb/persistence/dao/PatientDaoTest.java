package com.capgemini.wsb.persistence.dao;

import com.capgemini.wsb.persistence.dao.AddressDao;
import com.capgemini.wsb.persistence.entity.AddressEntity;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.persistence.entity.PatientEntity;
import com.capgemini.wsb.persistence.enums.Specialization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest
{
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given
        // when
        PatientEntity patientEntity = patientDao.findOne(1L);
        // then
        assertThat(patientEntity).isNotNull();
        assertThat(patientEntity.getFirstName()).isEqualTo("Jan");
    }

    @Transactional
    @Test
    public void testShouldSavePatient() {
        // given
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setPatientNumber("111");
        patientEntity.setTelephoneNumber("1231");
        patientEntity.setEmail("xh8pW@example.com");
        patientEntity.setDateOfBirth(LocalDate.now());
        patientEntity.setVerified(true);
        patientEntity.setDayOff(0L);

        long entitiesNumBefore = patientDao.count();
        // when
        final PatientEntity saved = patientDao.save(patientEntity);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(patientDao.count()).isEqualTo(entitiesNumBefore+1);
    }

    @Transactional
    @Test
    public void testShouldSavePatientWithAddress() {
        // given
        PatientEntity patientEntity = new PatientEntity();
        patientEntity.setFirstName("John");
        patientEntity.setLastName("Doe");
        patientEntity.setPatientNumber("111");
        patientEntity.setTelephoneNumber("1231");
        patientEntity.setEmail("xh8pW@example.com");
        patientEntity.setDateOfBirth(LocalDate.now());
        patientEntity.setVerified(true);
        patientEntity.setDayOff(0L);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");

        patientEntity.setAddresses(Collections.singleton(addressEntity));
        long entitiesNumBefore = patientDao.count();
        long addressEntitiesNumBefore = addressDao.count();

        // when
        final PatientEntity saved = patientDao.save(patientEntity);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(patientDao.count()).isEqualTo(entitiesNumBefore+1);
        assertThat(addressDao.count()).isEqualTo(addressEntitiesNumBefore+1);
    }

    @Transactional
    @Test
    public void testRemovePatientShouldRemoveVisitSNotDoctor() {
        // given
        // when
        final DoctorEntity doctor = doctorDao.findOne(3L);
        final PatientEntity patient = patientDao.findOne(3L);

        assertThat(patient.getVisits().size()).isEqualTo(1);
        assertThat(doctor.getVisits().size()).isEqualTo(2);
        // when
        patientDao.delete(patient.getId());
        // then
        assertThat(doctorDao.count()).isEqualTo(3);
        assertThat(patientDao.count()).isEqualTo(2);
    }

    @Transactional
    @Test
    public void testShouldFindPatientByLastName() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findByLastName("Kowalski");
        // then
        assertThat(patients).hasSize(2);
    }

    @Transactional
    @Test
    public void testShouldFindPatientByDayOffGreaterThan() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findByDayOffGreaterThan(0L);
        // then
        assertThat(patients).hasSize(2);
    }

    @Transactional
    @Test
    public void testShouldFindPatientByVisitsCountGreaterThan() {
        // given
        // when
        List<PatientEntity> patients = patientDao.findByVisitsCountGreaterThan(1L);
        // then
        assertThat(patients).hasSize(1);
    }
}
