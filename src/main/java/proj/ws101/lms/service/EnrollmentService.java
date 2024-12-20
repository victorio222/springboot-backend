package proj.ws101.lms.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import proj.ws101.lms.entity.Enrollment;
import proj.ws101.lms.repository.EnrollmentRepository;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    public EnrollmentService(@Autowired EnrollmentRepository enrollmentRepository){
        this.enrollmentRepository = enrollmentRepository;
    }

    public void enroll(Enrollment enrollment){
        enrollmentRepository.save(enrollment);
    }

    public void dropEnrollment(Long id){
            enrollmentRepository.deleteById(id);
    }
}
