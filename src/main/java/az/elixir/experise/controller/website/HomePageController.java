package az.elixir.experise.controller.website;

import javax.servlet.http.HttpSession;

import az.elixir.experise.service.website.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomePageController {

  @Autowired private ServiceEntityService service;

  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private TestimonialsService testimonialsService;

  @Autowired private PartnerService partnerService;

  @Autowired private FooterService footerService;

  @Autowired private CoursesService coursesService;

  @Autowired private BannerService bannerService;
  @Autowired private PlaceEntityService place;

  @RequestMapping(value = "/", method = RequestMethod.GET)
  public String homePage(Model model, HttpSession session) {
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
      model.addAttribute("testimonial", testimonialsService.findAll(langCode));
      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));
      model.addAttribute("banner", bannerService.findAll(langCode));
      model.addAttribute("service", service.findServicesByLanguage(langCode));
      model.addAttribute("footer", footerService.find(langCode));

      model.addAttribute("partner", partnerService.findAll());
      model.addAttribute("place", place.findAllPlaces());
      model.addAttribute("title", "Home");

    } catch (Exception exception) {
      return "website/index.html";
    }
    return "website/index.html";
  }
}
