package az.elixir.experise.controller;

import az.elixir.experise.dto.AllDegreesAndCredentialsView;
import az.elixir.experise.dto.AllResearchAndWritingsView;
import az.elixir.experise.dto.DegreesAndCredentialsView;
import az.elixir.experise.dto.ResearchAndWritingsView;
import az.elixir.experise.service.DegreesAndCredentialsService;
import az.elixir.experise.service.ResearchAndWritingsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class ResearchAndWritingsController {

    @Autowired
    private ResearchAndWritingsService researchAndWritingsService;


    @RequestMapping(value = "/research-and-writings/{langCode}", method = RequestMethod.GET)
    public String researchAndWritingsPage(@PathVariable("langCode") String langCode, Model model){
        try {
            List<AllResearchAndWritingsView> result = researchAndWritingsService.findAll();

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);

        }catch (Exception exception){
            return "index.html";
        }
        return "researchAndWritings.html";
    }

    @RequestMapping(value = "/research-and-writings-details/{langCode}/{id}", method = RequestMethod.GET)
    public String researchAndWritingsDetailsPage(@PathVariable("langCode") String langCode, @PathVariable("id") String id, Model model){
        try {
            ResearchAndWritingsView result = researchAndWritingsService.findById(Integer.parseInt(id));

            model.addAttribute("result", result);

        }catch (Exception exception){
            return "index.html";
        }
        return "researchAndWritingsDetails.html";
    }

}
