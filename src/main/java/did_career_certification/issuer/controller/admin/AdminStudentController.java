package did_career_certification.issuer.controller.admin;

import did_career_certification.issuer.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/issuer")
@RequiredArgsConstructor
public class AdminStudentController {

    private final StudentService studentService;

    @GetMapping
    public String getStudentList(Model model, Pageable pageable) {
        model.addAttribute("studentList", studentService.findAll(pageable));
        return "student-management-page";
    }
}
