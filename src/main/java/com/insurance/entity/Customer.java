package com.insurance.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "pesel")
    private String pesel;
    @Column(name = "signing_date")
    private LocalDate signingDate;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "insurance_customer",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "insurance_id")
    )
    private List<Insurance> insurances;

    public Customer() {

    }

    public List<Insurance> getInsurances() {
        return insurances;
    }

    public void setInsurances(List<Insurance> insurances) {
        this.insurances = insurances;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPesel() {
        return pesel;
    }

    public void setPesel(String pesel) {
        this.pesel = pesel;
    }

    public LocalDate getSigningDate() {
        return signingDate;
    }

    public void setSigningDate(LocalDate signingDate) {
        this.signingDate = signingDate;
    }

    public void addInsurance(Insurance insurance) {
        if (insurances == null) {
            insurances = new ArrayList<>();
        }
        insurances.add(insurance);
    }

}
