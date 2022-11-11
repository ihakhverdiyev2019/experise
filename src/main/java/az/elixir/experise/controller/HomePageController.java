package az.elixir.experise.controller;

import az.elixir.experise.dto.HomePage;
import az.elixir.experise.dto.PlaceView;
import az.elixir.experise.dto.ServiceView;
import az.elixir.experise.service.PlaceEntityService;
import az.elixir.experise.service.ServiceEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class HomePageController {

    @Autowired
    private ServiceEntityService service;

    @Autowired
    private PlaceEntityService place;

    @RequestMapping(value = "/home/{langCode}", method = RequestMethod.GET)
    public String homePage(@PathVariable("langCode") String langCode, Model model){
        try {
            HomePage homePage = new HomePage();
            List<ServiceView> serviceView = service.findServicesByLanguage(langCode);
            List<PlaceView> placeView = place.findAllPlaces();

            System.out.print("Size: " + serviceView.size());
            homePage.setServiceView(serviceView);
            homePage.setPlaceView(placeView);
            model.addAttribute("home", homePage);
            model.addAttribute("serviceViewSize", serviceView.size());
            model.addAttribute("placeViewSize", placeView.size());
        }catch (Exception exception){
            return "index.html";
        }
        return "index.html";
    }


}
