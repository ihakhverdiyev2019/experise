package az.elixir.experise.controller;

import az.elixir.experise.dto.AllResearchAndWritingsView;
import az.elixir.experise.dto.AllScholarshipView;
import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.dto.ScholarshipView;
import az.elixir.experise.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ScholarshipController {

    @Autowired
    private ScholarshipService scholarshipService;

    @RequestMapping(value = "/scholarship/{langCode}", method = RequestMethod.GET)
    public String scholarshipsPage(@PathVariable("langCode") String langCode, Model model){
        try {
            List<AllScholarshipView> result = scholarshipService.findAll();

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);

        }catch (Exception exception){
            return "index.html";
        }
        return "researchAndWritings.html";
    }

    @RequestMapping(value = "/scholarship/{langCode}/{id}", method = RequestMethod.GET)
    public String scholarshipDetailsPage(@PathVariable("langCode") String langCode, @PathVariable("id") String id, Model model){
        try {
            ScholarshipView result = scholarshipService.findById(Integer.parseInt(id));

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);
        }catch (Exception exception){
            return "index.html";
        }
        return "researchAndWritingsDetails.html";
    }


}
