package did_career_certification.issuer.service;

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
}
