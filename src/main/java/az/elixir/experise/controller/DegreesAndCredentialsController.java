package az.elixir.experise.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.*;
import az.elixir.experise.service.CoursesService;
import az.elixir.experise.service.DegreesAndCredentialsService;
import az.elixir.experise.service.ResearchAndWritingsService;
import az.elixir.experise.service.ScholarshipService;

@Controller
public class DegreesAndCredentialsController {

  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private CoursesService coursesService;

  @RequestMapping(value = "/degrees-and-credentials", method = RequestMethod.GET)
  public String degreesAndCredentialsPage(Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      List<AllDegreesAndCredentialsView> degrees =
          degreesAndCredentialsService.findAllByCategory("degrees", langCode);
      List<AllDegreesAndCredentialsView> others =
          degreesAndCredentialsService.findAllByCategory("others", langCode);
      List<AllDegreesAndCredentialsView> preparatory =
          degreesAndCredentialsService.findAllByCategory("preparatory", langCode);

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("degree", degrees);
      model.addAttribute("others", others);
      model.addAttribute("preparatory", preparatory);
      model.addAttribute("lang", langCode);

    } catch (Exception exception) {
      return "index.html";
    }
    return "degreesAndCredentials.html";
  }

  @RequestMapping(value = "/degrees-and-credentials-details/{id}", method = RequestMethod.GET)
  public String degreesAndCredentialsDetailsPage(
      @PathVariable("id") String id, Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      DegreesAndCredentialsView degreeDetails =
          degreesAndCredentialsService.findById(Integer.parseInt(id), langCode);

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("degree", degreeDetails);
      model.addAttribute("title", degreeDetails.getName());

    } catch (Exception exception) {
      return "index.html";
    }
    return "degreesAndCredentailsDetails.html";
  }
}
