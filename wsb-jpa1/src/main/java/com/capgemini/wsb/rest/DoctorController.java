package com.capgemini.wsb.rest;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.rest.exception.EntityNotFoundException;
import com.capgemini.wsb.service.DoctorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping("/doctor")
    public List<DoctorTO> findAll() {
        return doctorService.findAll();
    }

    @GetMapping("/doctor/{id}")
    public DoctorTO findBaId(@PathVariable final Long id) {
        final DoctorTO doctor = doctorService.findById(id);
        if(doctor != null)
        {
            return doctor;
        }
        throw new EntityNotFoundException(id);
    }

}
