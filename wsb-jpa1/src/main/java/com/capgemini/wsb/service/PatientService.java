package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.PatientTO;

import java.util.List;

public interface PatientService {

    public PatientTO findById(final Long id);

    public List<PatientTO> findAll();

}
