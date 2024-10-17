package com.AbdProject.firstjobapp.job.impl;

import com.AbdProject.firstjobapp.job.Job;
import com.AbdProject.firstjobapp.job.JobRepository;
import com.AbdProject.firstjobapp.job.JobService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JobServiceImpl implements JobService {
    JobRepository jobRepository;

    public JobServiceImpl(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    private Long nextId = 1L;
    @Override
    public List<Job> findAll() {
        return jobRepository.findAll();
    }

    @Override
    public void createJob(Job job) {
        job.setId(nextId++);
        jobRepository.save(job);
    }

    @Override
    public Job getJobById(Long id) {
      return  jobRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteJobById(Long id) {
        try {
            jobRepository.deleteById(id);
            return true;
        }catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean updateJobById(Long id, Job job) {
        Optional<Job> jobOptional = jobRepository.findById(id);
        if(jobOptional.isPresent()) {
            Job j = jobOptional.get();
            j.setDescription(job.getDescription());
            j.setLocation(job.getLocation());
            j.setTitle(job.getTitle());
            j.setMaxSalary(job.getMaxSalary());
            j.setMinSalary(job.getMinSalary());
            jobRepository.save(j);
            return true;
        }


        return false;
    }

}
