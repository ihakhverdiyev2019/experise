package az.elixir.experise.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.service.*;

@Controller
public class FAQController {

  @Autowired private FAQService faqService;
  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private CoursesService coursesService;

  @RequestMapping(value = "/faq", method = RequestMethod.GET)
  public String researchAndWritingsPage(Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("faq", faqService.findAll(langCode));
      model.addAttribute("lang", langCode);
      model.addAttribute("title", "FAQ");

    } catch (Exception exception) {
      return "index.html";
    }
    return "faq.html";
  }
}
