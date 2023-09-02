package az.elixir.experise.controller.website;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.website.ScholarshipView;
import az.elixir.experise.service.website.*;

@Controller
public class ScholarshipController {

  @Autowired private ScholarshipService scholarshipService;
  @Autowired private FooterService footerService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private CoursesService coursesService;
  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @RequestMapping(value = "/scholarship/{id}", method = RequestMethod.GET)
  public String scholarshipDetailsPage(
      @PathVariable("id") String id, Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      ScholarshipView result = scholarshipService.findById(Integer.parseInt(id), langCode);
      model.addAttribute("seo", scholarshipService.seoDetails(langCode));
      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));
      model.addAttribute("footer", footerService.find(langCode));
      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("result", result);
      model.addAttribute("lang", langCode);
      model.addAttribute("title", result.getName());

    } catch (Exception exception) {
      return "website/index.html";
    }
    return "website/scholarshipDetails.html";
  }
}
