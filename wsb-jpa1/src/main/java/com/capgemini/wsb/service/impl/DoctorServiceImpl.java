package com.capgemini.wsb.service.impl;

import com.capgemini.wsb.dto.DoctorTO;
import com.capgemini.wsb.mapper.DoctorMapper;
import com.capgemini.wsb.persistence.dao.DoctorDao;
import com.capgemini.wsb.persistence.entity.DoctorEntity;
import com.capgemini.wsb.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao pDoctorDao)
    {
        doctorDao = pDoctorDao;
    }

    @Override
    public DoctorTO findById(final Long id) {
        final DoctorEntity entity = doctorDao.findOne(id);
        return DoctorMapper.mapToTO(entity);
    }

    @Override
    public List<DoctorTO> findAll() {
        final List<DoctorEntity> entities = doctorDao.findAll();
        return entities.stream().map(DoctorMapper::mapToTO).collect(Collectors.toList());
    }

}
