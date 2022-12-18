package az.elixir.experise.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.AllBlogsView;
import az.elixir.experise.dto.BlogView;
import az.elixir.experise.model.BlogCategoryEntity;
import az.elixir.experise.service.*;

@Controller
public class BlogController {

  @Autowired private BlogService blogService;

  @Autowired private CoursesService coursesService;
  @Autowired private DegreesAndCredentialsService degreesAndCredentialsService;

  @Autowired private ScholarshipService scholarshipService;

  @Autowired private ResearchAndWritingsService researchAndWritingsService;

  @RequestMapping(value = "/blog/{page}", method = RequestMethod.GET)
  public String blogsPage(@PathVariable("page") String page, Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      List<AllBlogsView> result = blogService.getAllBlogs(Integer.parseInt(page), langCode);
      int num = blogService.numOfPage();

      List<BlogCategoryEntity> category = blogService.getCategoryDetails(langCode);

      model.addAttribute("category", category);
      model.addAttribute("pageNum", num);
      model.addAttribute("currNum", Integer.parseInt(page));

      model.addAttribute("result", result);
      model.addAttribute("lang", langCode);

      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("title", "BLOG");

    } catch (Exception exception) {
      System.out.printf("Exception: " + exception.getMessage());
      return "index.html";
    }
    return "blogs.html";
  }

  @RequestMapping(value = "/blog/{page}/{categoryId}", method = RequestMethod.GET)
  public String blogsCategoryPage(
      @PathVariable("page") String page,
      @PathVariable("categoryId") String categoryId,
      Model model,
      HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      String category = blogService.category(Integer.parseInt(categoryId), langCode);
      List<AllBlogsView> result =
          blogService.getAllBlogsByCategory(
              Integer.parseInt(page), Integer.parseInt(categoryId), langCode);
      int num = blogService.numOfPageByCategory(Integer.parseInt(categoryId), langCode);

      model.addAttribute("categoryId", categoryId);
      model.addAttribute("pageNum", num);
      model.addAttribute("currNum", Integer.parseInt(page));

      model.addAttribute("result", result);
      model.addAttribute("lang", langCode);

      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("title", "Blog - " + category);

    } catch (Exception exception) {
      System.out.printf("Exception: " + exception.getMessage());
      return "index.html";
    }
    return "blogs-category.html";
  }

  @RequestMapping(value = "/blog-details/{blogId}", method = RequestMethod.GET)
  public String blogDetails(
      @PathVariable("blogId") String blogId, Model model, HttpSession session) {
    String langCode = null;
    try {
      if (session.getAttribute("lang") == null && langCode == null) {
        langCode = "EN";
        session.setAttribute("lang", langCode);
      } else {
        langCode = session.getAttribute("lang").toString();
      }
      BlogView result = blogService.blogDetails(Integer.parseInt(blogId), langCode);

      model.addAttribute("result", result);
      model.addAttribute("lang", langCode);

      model.addAttribute("degreesFooter", degreesAndCredentialsService.isFooter(langCode));

      model.addAttribute("degrees", degreesAndCredentialsService.findAll(langCode));
      model.addAttribute("scholar", scholarshipService.findAll(langCode));
      model.addAttribute("courses", coursesService.findAll(langCode));
      model.addAttribute("academic", researchAndWritingsService.findAll(langCode));

      model.addAttribute("title", result.getName());

    } catch (Exception exception) {
      System.out.printf("Exception: " + exception.getMessage());
      return "index.html";
    }
    return "blogDetails.html";
  }
}
