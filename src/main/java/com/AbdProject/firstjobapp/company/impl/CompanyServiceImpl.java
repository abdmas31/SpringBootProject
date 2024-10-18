package com.AbdProject.firstjobapp.company.impl;

import com.AbdProject.firstjobapp.company.Company;
import com.AbdProject.firstjobapp.company.CompanyController;
import com.AbdProject.firstjobapp.company.CompanyRepository;
import com.AbdProject.firstjobapp.company.CompanyService;
import com.AbdProject.firstjobapp.review.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CompanyServiceImpl implements CompanyService {
    CompanyRepository companyRepository;
    ReviewRepository reviewRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository,ReviewRepository reviewRepository) {
        this.companyRepository = companyRepository;
        this.reviewRepository = reviewRepository;
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
            Company company = companyRepository.findById(id).orElse(null);
            assert company != null;
            reviewRepository.findByCompanyId(id).forEach(review -> reviewRepository.deleteAll());
            companyRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
