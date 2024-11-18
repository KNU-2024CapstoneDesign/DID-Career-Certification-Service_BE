package did_career_certification.holder.controller.admin;

import did_career_certification.global.dto.VC;
import did_career_certification.holder.dto.UpdateVCRequest;
import did_career_certification.holder.dto.UpdateVCResponse;
import did_career_certification.holder.service.VCService;
import did_career_certification.issuer.enums.AcademicStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin/holder/vc")
@RequiredArgsConstructor
public class AdminVCController {

    private final VCService vcService;

    @GetMapping
    public String getVCList(Model model) {
        model.addAttribute("vcList", vcService.findAll());
        return "holder/vc-management-page";
    }

    @GetMapping("/{vcId}")
    public String getVCForm(@PathVariable Long vcId, Model model) {
        UpdateVCResponse vc = vcService.getVC(vcId);
        model.addAttribute("vc", vc);
        model.addAttribute("academicStatus", AcademicStatus.values());
        return "holder/vc-update-form";
    }

    @PutMapping("/{vcId}")
    public String updateVC(@PathVariable Long vcId, @Valid @ModelAttribute UpdateVCRequest request,
        BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "holder/vc-update-form";
        }
        vcService.updateVC(vcId, request);
        return "redirect:/admin/holder/vc";
    }
}
