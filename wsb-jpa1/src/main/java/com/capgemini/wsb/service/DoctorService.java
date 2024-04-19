package com.capgemini.wsb.service;

import com.capgemini.wsb.dto.DoctorTO;

import java.util.List;

public interface DoctorService {

    DoctorTO findById(final Long id);

    List<DoctorTO> findAll();
}
