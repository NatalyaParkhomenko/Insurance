package com.insurance.service;

import com.insurance.entity.Insurance;
import com.insurance.repository.InsuranceRepository;
import com.insurance.request.InsuranceReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InsuranceService {
    private final InsuranceRepository repository;

    @Autowired
    public InsuranceService(InsuranceRepository repository) {
        this.repository = repository;
    }

    public List<Insurance> findAll() {
        return repository.findAll();
    }

    public Insurance findById(long id) {
        return repository.findById(id).get();
    }

    public void deleteById(long id) {
        repository.deleteById(id);
    }

    public void save(InsuranceReq request) {
        Insurance insurance = new Insurance();
        insurance.setType(request.getType());
        insurance.setCoveragePercentage(request.getCoveragePercentage());
        repository.save(insurance);
    }
}
