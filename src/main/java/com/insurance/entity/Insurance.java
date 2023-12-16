package com.insurance.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "insurance")
public class Insurance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "type")
    private String type;
    @Column(name = "coverage_percentage")
    private int coveragePercentage;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "insurance_customer",
            joinColumns = @JoinColumn(name = "insurance_id"),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getCoveragePercentage() {
        return coveragePercentage;
    }

    public void setCoveragePercentage(int coveragePercentage) {
        this.coveragePercentage = coveragePercentage;
    }
}
