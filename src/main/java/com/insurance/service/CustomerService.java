package com.insurance.service;

import com.insurance.entity.Customer;
import com.insurance.entity.Insurance;
import com.insurance.repository.CustomerRepository;
import com.insurance.repository.InsuranceRepository;
import com.insurance.request.CustomerReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    private final CustomerRepository repository;
    private final InsuranceRepository insuranceRepository;

    @Autowired
    public CustomerService(CustomerRepository repository, InsuranceRepository insuranceRepository) {
        this.repository = repository;
        this.insuranceRepository = insuranceRepository;
    }

    public List<Customer> findAllByInsuranceId(long insuranceId) {
        return repository.findAllByInsurances_Id(insuranceId);
    }

    public Customer findById(long id) {
        Customer customer = repository.findById(id).get();
        customer.setInsurances(null);
        return customer;
    }
    public void deleteById(long id) {
        Customer customer= repository.findById(id).get();
        customer.setInsurances(null);// TODO ПОЧЕМУ НАДО СТАВИТЬ NULL НА РОДИТЕЛИ
        repository.delete(customer);
    }

    public void save(CustomerReq request) {
        Insurance insurance = insuranceRepository.findById(request.getInsuranceId()).get();
        Customer customer = new Customer();
        customer.setPesel(request.getPesel());
        customer.setSigningDate(request.getSigningDate());
        customer.addInsurance(insurance);
        repository.save(customer);
    }

}
