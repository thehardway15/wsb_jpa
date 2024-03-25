package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.PatientTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.PatientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/patient")
    public List<PatientTO> findAll() {
        return patientService.findAll();
    }

    @GetMapping("/patient/{id}")
    public PatientTO findBaId(@PathVariable final Long id) {
        final PatientTO patient = patientService.findById(id);
        if(patient != null)
        {
            return patient;
        }
        throw new EntityNotFoundException(id);
    }

}
