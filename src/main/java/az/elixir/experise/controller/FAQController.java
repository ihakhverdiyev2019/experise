package az.elixir.experise.controller;

import az.elixir.experise.service.FAQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class FAQController {

    @Autowired
    private FAQService faqService;


    @RequestMapping(value = "/faq/{langCode}", method = RequestMethod.GET)
    public String researchAndWritingsPage(@PathVariable("langCode") String langCode, Model model){
        try {

            model.addAttribute("faq", faqService.findAll());
            model.addAttribute("lang", langCode);

        }catch (Exception exception){
            return "index.html";
        }
        return "faq.html";
    }
}
