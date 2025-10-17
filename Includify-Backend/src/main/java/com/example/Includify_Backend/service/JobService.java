package com.example.Includify_Backend.service;

import com.includify.entity.*;
import com.includify.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
public class JobService {
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository jobAppRepo;
    private final SavedJobRepository savedJobRepo;

    public JobService(JobPostRepository jobPostRepository, UserRepository userRepository,
                      JobApplicationRepository jobAppRepo, SavedJobRepository savedJobRepo) {
        this.jobPostRepository = jobPostRepository;
        this.userRepository = userRepository;
        this.jobAppRepo = jobAppRepo;
        this.savedJobRepo = savedJobRepo;
    }

    public JobPost createJob(UUID posterId, JobPost jp){
        User poster = userRepository.findById(posterId).orElseThrow(() -> new RuntimeException("Poster not found"));
        jp.setPoster(poster);
        return jobPostRepository.save(jp);
    }

    public JobPost updateJob(UUID posterId, UUID jobId, JobPost update){
        JobPost existing = jobPostRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        if(!existing.getPoster().getId().equals(posterId)) throw new RuntimeException("Not allowed");
        existing.setTitle(update.getTitle());
        existing.setDescription(update.getDescription());
        existing.setLocation(update.getLocation());
        existing.setSalaryMin(update.getSalaryMin());
        existing.setSalaryMax(update.getSalaryMax());
        return jobPostRepository.save(existing);
    }

    public void deleteJob(UUID posterId, UUID jobId){
        JobPost existing = jobPostRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        if(!existing.getPoster().getId().equals(posterId)) throw new RuntimeException("Not allowed");
        jobPostRepository.delete(existing);
    }

    public List<JobPost> browseJobs(){ return jobPostRepository.findByActiveTrue(); }

    public JobApplication applyToJob(UUID applicantId, UUID jobId, String coverLetter){
        User applicant = userRepository.findById(applicantId).orElseThrow(() -> new RuntimeException("Applicant not found"));
        JobPost job = jobPostRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        JobApplication ja = new JobApplication();
        ja.setApplicant(applicant);
        ja.setJobPost(job);
        ja.setCoverLetter(coverLetter);
        return jobAppRepo.save(ja);
    }

    public SavedJob saveJob(UUID userId, UUID jobId){
        var opt = savedJobRepo.findByUserIdAndJobPostId(userId, jobId);
        if(opt.isPresent()) return opt.get();
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        JobPost job = jobPostRepository.findById(jobId).orElseThrow(() -> new RuntimeException("Job not found"));
        SavedJob s = new SavedJob();
        s.setUser(user);
        s.setJobPost(job);
        return savedJobRepo.save(s);
    }

    public void unsaveJob(UUID userId, UUID jobId){
        var opt = savedJobRepo.findByUserIdAndJobPostId(userId, jobId);
        opt.ifPresent(savedJobRepo::delete);
    }

    public List<JobApplication> listApplicationsForJob(UUID jobId){
        return jobAppRepo.findByJobPostId(jobId);
    }
}
