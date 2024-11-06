package did_career_certification.issuer.controller.admin;

import did_career_certification.issuer.entity.Student;
import did_career_certification.issuer.enums.AcademicStatus;
import did_career_certification.issuer.enums.College;
import did_career_certification.issuer.enums.Degree;
import did_career_certification.issuer.enums.Major;
import did_career_certification.issuer.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/issuer/students")
@RequiredArgsConstructor
public class AdminStudentController {

    private final StudentService studentService;

    @GetMapping
    public String getStudentList(Model model, Pageable pageable) {
        model.addAttribute("studentList", studentService.findAll(pageable));
        return "student-management-page";
    }

    @GetMapping("/register")
    public String registerStudent(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("colleges", College.values());
        model.addAttribute("majors", Major.values());
        model.addAttribute("degrees", Degree.values());
        model.addAttribute("academicStatus", AcademicStatus.values());
        return "student-register-form";
    }
}
