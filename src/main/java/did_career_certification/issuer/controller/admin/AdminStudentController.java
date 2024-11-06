package did_career_certification.issuer.controller.admin;

import did_career_certification.issuer.dto.RegisterStudentRequest;
import did_career_certification.issuer.entity.Student;
import did_career_certification.issuer.enums.AcademicStatus;
import did_career_certification.issuer.enums.College;
import did_career_certification.issuer.enums.Degree;
import did_career_certification.issuer.enums.Major;
import did_career_certification.issuer.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/issuer/students")
@RequiredArgsConstructor
public class AdminStudentController {

    private final StudentService studentService;

    @GetMapping
    public String getStudentList(Model model, Pageable pageable) {
        model.addAttribute("studentList", studentService.findAll(pageable));
        return "issuer/student-management-page";
    }

    @GetMapping("/register")
    public String getStudentRegisterForm(Model model) {
        model.addAttribute("student", new Student());
        model.addAttribute("colleges", College.values());
        model.addAttribute("majors", Major.values());
        model.addAttribute("degrees", Degree.values());
        model.addAttribute("academicStatus", AcademicStatus.values());
        return "issuer/student-register-form";
    }

    @PostMapping
    public String registerStudent(@Valid @ModelAttribute RegisterStudentRequest request, BindingResult bindingResult,
        Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "issuer/student-register-form";
        }
        studentService.registerStudent(request);
        return "redirect:/admin/issuer/students";
    }

    @GetMapping("/{id}/update")
    public String getStudentUpdateForm(@PathVariable Long id, Model model) {
        Student student = studentService.findById(id);
        model.addAttribute("student", student);
        model.addAttribute("colleges", College.values());
        model.addAttribute("majors", Major.values());
        model.addAttribute("degrees", Degree.values());
        model.addAttribute("academicStatus", AcademicStatus.values());
        return "issuer/student-update-form";
    }
}
