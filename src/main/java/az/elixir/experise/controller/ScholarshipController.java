package az.elixir.experise.controller;

import az.elixir.experise.dto.AllResearchAndWritingsView;
import az.elixir.experise.dto.AllScholarshipView;
import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.dto.ScholarshipView;
import az.elixir.experise.service.CoursesService;
import az.elixir.experise.service.DegreesAndCredentialsService;
import az.elixir.experise.service.ResearchAndWritingsService;
import az.elixir.experise.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private ResearchAndWritingsService researchAndWritingsService;

    @Autowired
    private CoursesService coursesService;
    @Autowired
    private DegreesAndCredentialsService degreesAndCredentialsService;

    @RequestMapping(value = "/scholarship/{id}", method = RequestMethod.GET)
    public String scholarshipDetailsPage(@PathVariable("id") String id, Model model, HttpSession session){
        String langCode = null;
        try {
            if (session.getAttribute("lang") == null && langCode == null){
                langCode = "EN";
                session.setAttribute("lang", langCode);
            }
            ScholarshipView result = scholarshipService.findById(Integer.parseInt(id));
            model.addAttribute("degrees", degreesAndCredentialsService.findAll());
            model.addAttribute("scholar", scholarshipService.findAll());
            model.addAttribute("courses", coursesService.findAll());
            model.addAttribute("academic", researchAndWritingsService.findAll());

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);
            model.addAttribute("title", result.getName());

        }catch (Exception exception){
            return "index.html";
        }
        return "scholarshipDetails.html";
    }


}
