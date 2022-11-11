package az.elixir.experise.controller;

import az.elixir.experise.dto.*;
import az.elixir.experise.service.DegreesAndCredentialsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class DegreesAndCredentialsController {

    @Autowired
    private DegreesAndCredentialsService degreesAndCredentialsService;


    @RequestMapping(value = "/degrees-and-credentials/{langCode}", method = RequestMethod.GET)
    public String degreesAndCredentialsPage(@PathVariable("langCode") String langCode, Model model){
        try {
            List<AllDegreesAndCredentialsView> degrees = degreesAndCredentialsService.findAllByCategory("degrees");
            List<AllDegreesAndCredentialsView> others = degreesAndCredentialsService.findAllByCategory("others");
            List<AllDegreesAndCredentialsView> preparatory = degreesAndCredentialsService.findAllByCategory("preparatory");

            model.addAttribute("degree", degrees);
            model.addAttribute("others", others);
            model.addAttribute("preparatory", preparatory);
            model.addAttribute("lang", langCode);

        }catch (Exception exception){
            return "index.html";
        }
        return "degreesAndCredentials.html";
    }

    @RequestMapping(value = "/degrees-and-credentials-details/{langCode}/{id}", method = RequestMethod.GET)
    public String degreesAndCredentialsDetailsPage(@PathVariable("langCode") String langCode, @PathVariable("id") String id, Model model){
        try {
            DegreesAndCredentialsView degreeDetails = degreesAndCredentialsService.findById(Integer.parseInt(id));

            model.addAttribute("degree", degreeDetails);


        }catch (Exception exception){
            return "index.html";
        }
        return "degreesAndCredentailsDetails.html";
    }

}
