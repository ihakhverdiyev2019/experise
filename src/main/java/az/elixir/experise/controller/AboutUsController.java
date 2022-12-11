package az.elixir.experise.controller;

import az.elixir.experise.dto.AboutView;
import az.elixir.experise.dto.AllCoursesView;
import az.elixir.experise.dto.CoursesView;
import az.elixir.experise.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AboutUsController {

    @Autowired
    private AboutService aboutService;
    @Autowired
    private DegreesAndCredentialsService degreesAndCredentialsService;

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private ResearchAndWritingsService researchAndWritingsService;

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/about-us", method = RequestMethod.GET)
    public String aboutUs(Model model, HttpSession session){
        String langCode = null;
        try {
            if (session.getAttribute("lang") == null && langCode == null){
                langCode = "EN";
                session.setAttribute("lang", langCode);
            }
            AboutView result = aboutService.findAbout();

            model.addAttribute("degrees", degreesAndCredentialsService.findAll());
            model.addAttribute("scholar", scholarshipService.findAll());
            model.addAttribute("courses", coursesService.findAll());
            model.addAttribute("academic", researchAndWritingsService.findAll());
            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);
            model.addAttribute("title", "About Us");


        }catch (Exception exception){
            return "index.html";
        }
        return "about.html";
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
//            return "index.html";
//        }
//        return "contact.html";
//    }
}
