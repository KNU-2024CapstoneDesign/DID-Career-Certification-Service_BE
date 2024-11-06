package did_career_certification.issuer.service;

import did_career_certification.exception.NotFoundException;
import did_career_certification.issuer.dto.RegisterStudentRequest;
import did_career_certification.issuer.entity.Student;
import did_career_certification.issuer.repository.StudentRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable).stream().toList();
    }

    public Student findById(Long id) {
        return studentRepository.findById(id)
            .orElseThrow(() -> new NotFoundException("not.found.user"));
    }

    public void registerStudent(RegisterStudentRequest request) {
        Student student = request.toEntity();
        studentRepository.save(student);
    }
}
