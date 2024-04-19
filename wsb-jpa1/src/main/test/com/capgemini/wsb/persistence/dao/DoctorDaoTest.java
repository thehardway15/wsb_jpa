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
public class DoctorDaoTest
{
    @Autowired
    private AddressDao addressDao;
    @Autowired
    private PatientDao patientDao;
    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldFindDoctorById() {
        // given
        // when
        DoctorEntity doctorEntity = doctorDao.findOne(1L);
        // then
        assertThat(doctorEntity).isNotNull();
        assertThat(doctorEntity.getSpecialization()).isEqualTo(Specialization.SURGEON);
    }

    @Transactional
    @Test
    public void testShouldSaveDoctor() {
        // given
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName("Doctor");
        doctorEntity.setLastName("Doctor");
        doctorEntity.setDoctorNumber("523456789");
        doctorEntity.setTelephoneNumber("523456789");
        doctorEntity.setEmail("xh8pW@example.com");
        doctorEntity.setSpecialization(Specialization.SURGEON);

        long entitiesNumBefore = doctorDao.count();
        // when
        final DoctorEntity saved = doctorDao.save(doctorEntity);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(doctorDao.count()).isEqualTo(entitiesNumBefore+1);
    }

    @Transactional
    @Test
    public void testShouldSaveDoctorWithAddress() {
        // given
        DoctorEntity doctorEntity = new DoctorEntity();
        doctorEntity.setFirstName("Doctor");
        doctorEntity.setLastName("Doctor");
        doctorEntity.setDoctorNumber("523456789");
        doctorEntity.setTelephoneNumber("523456789");
        doctorEntity.setEmail("xh8pW@example.com");
        doctorEntity.setSpecialization(Specialization.SURGEON);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setAddressLine1("line1");
        addressEntity.setAddressLine2("line2");
        addressEntity.setCity("City1");
        addressEntity.setPostalCode("66-666");

        doctorEntity.setAddresses(Collections.singleton(addressEntity));
        long entitiesNumBefore = doctorDao.count();
        long addressEntitiesNumBefore = addressDao.count();

        // when
        final DoctorEntity saved = doctorDao.save(doctorEntity);
        // then
        assertThat(saved).isNotNull();
        assertThat(saved.getId()).isNotNull();
        assertThat(doctorDao.count()).isEqualTo(entitiesNumBefore+1);
        assertThat(addressDao.count()).isEqualTo(addressEntitiesNumBefore+1);
    }

    @Transactional
    @Test
    public void testRemoveDoctorShouldRemoveVisitSNotPatient() {
        // given
        // when
        final DoctorEntity doctor = doctorDao.findOne(3L);
        final PatientEntity patient = patientDao.findOne(3L);

        assertThat(patient.getVisits().size()).isEqualTo(1);
        assertThat(doctor.getVisits().size()).isEqualTo(2);
        // when
        doctorDao.delete(doctor.getId());
        // then
        assertThat(doctorDao.count()).isEqualTo(2);
        assertThat(patientDao.count()).isEqualTo(3);
    }
}
