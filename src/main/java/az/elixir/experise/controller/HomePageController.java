package az.elixir.experise.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import az.elixir.experise.dto.HomePage;
import az.elixir.experise.dto.PlaceView;
import az.elixir.experise.service.*;

@Controller
public class HomePageController {

  @Autowired private ServiceEntityService service;

  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @Autowired private CoursesService coursesService;

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
      HomePage homePage = new HomePage();
      //            List<ServiceView> serviceView =
      // service.findServicesByLanguage(session.getAttribute("lang").toString());
      List<PlaceView> placeView = place.findAllPlaces();
      //            homePage.setServiceView(serviceView);
      homePage.setPlaceView(placeView);
      model.addAttribute("home", homePage);

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      //            model.addAttribute("serviceViewSize", serviceView.size());
      model.addAttribute("placeViewSize", placeView.size());
      model.addAttribute("title", "Home");

    } catch (Exception exception) {
      return "sdgadsf.html";
    }
    return "index.html";
  }
}
