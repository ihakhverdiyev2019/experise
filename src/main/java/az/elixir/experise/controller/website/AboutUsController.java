package az.elixir.experise.controller.website;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.website.AboutView;
import az.elixir.experise.service.website.*;

@Controller
public class AboutUsController {

  @Autowired private AboutService aboutService;
  @Autowired private FooterService footerService;

  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private CoursesService coursesService;

  @RequestMapping(value = "/about-us", method = RequestMethod.GET)
  public String aboutUs(Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      AboutView result = aboutService.findAbout(langCode);
      model.addAttribute("seo", aboutService.seoDetails(langCode));

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));
      model.addAttribute("footer", footerService.find(langCode));

      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("result", result);
      model.addAttribute("lang", langCode);
      model.addAttribute("title", "About Us");

    } catch (Exception exception) {
      return "website/index.html";
    }
    return "website/about.html";
  }

  //    @RequestMapping(value = "/contact", method = RequestMethod.GET)
  //    public String contact(Model model, HttpSession session){
  //        String langCode = null;
  //        try {
  //            if (session.getAttribute("lang") == null && langCode == null){
  //                langCode = "EN";
  //                session.setAttribute("lang", langCode);
  //            }
  //            CoursesView result = coursesService.findById(Integer.parseInt(id));
  //
  //            model.addAttribute("result", result);
  //            model.addAttribute("lang", langCode);
  //            model.addAttribute("title", result.getName());
  //
  //        }catch (Exception exception){
  //            return "admin-dashboard.html";
  //        }
  //        return "contact.html";
  //    }
}
