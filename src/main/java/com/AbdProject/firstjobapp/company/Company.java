package com.AbdProject.firstjobapp.company;

import com.AbdProject.firstjobapp.job.Job;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String description;
    private String phone;
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "company")
    private List<Job> jobs;



    public Company(Long id, String name, String address, String description, String phone, String email, List<Job> jobs) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.description = description;
        this.phone = phone;
        this.email = email;
        this.jobs = new ArrayList<>(jobs);
    }
    public Company() {
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<Job> getJobs() {
        return jobs;
    }

    public void setJobs(List<Job> jobs) {
        this.jobs = jobs;
    }
}