package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.dto.VisitTO;
import com.capgemini.wsb.service.PatientService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Transactional
    @Test
    public void testShouldFindPatientById() {
        // given

        // when
        final PatientTO patientTO = patientService.findById(1L);
        // then
        assertThat(patientTO).isNotNull();
        assertThat(patientTO.getId()).isEqualTo(1L);
        assertThat(patientTO.isVerified()).isTrue();
        assertThat(patientTO.getVisits().size()).isEqualTo(1);

        final VisitTO visit = patientTO.getVisits().iterator().next();
        assertThat(visit.getMedicalTreatments().size()).isEqualTo(1);
        assertThat(visit.getDoctor()).isNotNull();
        assertThat(visit.getPatient()).isNotNull();
        assertThat(visit.getDoctor().getId()).isEqualTo(1L);
        assertThat(visit.getPatient().getId()).isEqualTo(1L);
    }

    @Transactional
    @Test
    public void testRemovePatientShouldRemoveVisitSNotPatient() {
        // given
        // when
        patientService.deleteById(3L);
        // then
        assertThat(patientService.findAll().size()).isEqualTo(2);
        final DoctorTO doctorTO = doctorService.findById(3L);
        assertThat(doctorTO.getVisits().size()).isEqualTo(1);
    }

    @Transactional
    @Test
    public void testShouldReturnVisitsByPatientId() {
        // given
        // when
        final List<VisitTO> visits = patientService.findVisitsByPatientId(2L);
        // then
        assertThat(visits).hasSize(2);
    }

}