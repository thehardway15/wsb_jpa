package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;

import java.util.List;

public interface PatientService {

    PatientTO findById(final Long id);

    List<PatientTO> findAll();

    Boolean deleteById(final Long id);

}
