package az.elixir.experise.controller;

import az.elixir.experise.dto.AllCoursesView;
import az.elixir.experise.dto.AllScholarshipView;
import az.elixir.experise.dto.CoursesView;
import az.elixir.experise.dto.ScholarshipView;
import az.elixir.experise.service.CoursesService;
import az.elixir.experise.service.ScholarshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class CoursesController {

    @Autowired
    private CoursesService coursesService;

    @RequestMapping(value = "/courses/{langCode}", method = RequestMethod.GET)
    public String coursesPage(@PathVariable("langCode") String langCode, Model model){
        try {
            List<AllCoursesView> result = coursesService.findAll();

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);

        }catch (Exception exception){
            return "index.html";
        }
        return "courses.html";
    }

    @RequestMapping(value = "/courses/{langCode}/{id}", method = RequestMethod.GET)
    public String courseDetailsPage(@PathVariable("langCode") String langCode, @PathVariable("id") String id, Model model){
        try {
            CoursesView result = coursesService.findById(Integer.parseInt(id));

            model.addAttribute("result", result);
            model.addAttribute("lang", langCode);
        }catch (Exception exception){
            return "index.html";
        }
        return "courseDetails.html";
    }


}
