package com.AbdProject.firstjobapp.company.impl;

import com.AbdProject.firstjobapp.company.Company;
import com.AbdProject.firstjobapp.company.CompanyController;
import com.AbdProject.firstjobapp.company.CompanyRepository;
import com.AbdProject.firstjobapp.company.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }

    @Override
    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    @Override
    public boolean updateCompany(Long id, Company company) {
        Optional<Company> optionalCompany = companyRepository.findById(id);
        if (optionalCompany.isPresent()) {
            company.setId(id);
            company.setName(company.getName());
            company.setAddress(company.getAddress());
            company.setPhone(company.getPhone());
            company.setEmail(company.getEmail());
            company.setJobs(company.getJobs());
            company.setDescription(company.getDescription());
            companyRepository.save(company);
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCompany(Long id) {
        if(companyRepository.existsById(id)) {
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
