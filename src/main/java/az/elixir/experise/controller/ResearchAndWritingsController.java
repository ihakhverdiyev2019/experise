package az.elixir.experise.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.service.*;

@Controller
public class ResearchAndWritingsController {

  @Autowired private ResearchAndWritingsService researchAndWritingsService;
  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private CoursesService coursesService;
  @Autowired private FooterService footerService;

  @RequestMapping(value = "/research-and-writings-details/{id}", method = RequestMethod.GET)
  public String researchAndWritingsDetailsPage(
      @PathVariable("id") String id, Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      ResearchAndWritingsView result =
          researchAndWritingsService.findById(Integer.parseInt(id), langCode);

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));
      model.addAttribute("footer", footerService.find(langCode));

      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("result", result);
      model.addAttribute("title", result.getName());

    } catch (Exception exception) {
      return "index.html";
    }
    return "researchAndWritingsDetails.html";
  }
}
