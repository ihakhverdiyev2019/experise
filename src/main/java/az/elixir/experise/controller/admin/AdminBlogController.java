package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.blog.BlogCategoryUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminBlogService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminBlogController {

  @Autowired private UserService userService;

  @Autowired private LanguageService languageService;

  @Autowired private AdminBlogService adminBlogService;

  @RequestMapping(value = "/admin-elixir/blog-category-list", method = RequestMethod.GET)
  public String listOfBlogCategory(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminBlogService.blogCategoryList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          System.out.println(payload.getResult());
          model.addAttribute("blogCategories", payload.getResult());
          return "admin/admin-blog-categories.html";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      return "";
    }
  }

  @RequestMapping(value = "/admin-elixir/add-blog-category", method = RequestMethod.GET)
  public String addBlogCategory(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", "EN");
          return "admin/admin-blog-category-add.html";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      return "";
    }
  }

  @RequestMapping(value = "/admin-elixir/blog-category-remove/{id}", method = RequestMethod.GET)
  public String removeCourse(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminBlogService.removeBlogCategory(id);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/blog-category-list";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getMessage());
      //      return "aaaaa";
    }
    return null;
  }

  @RequestMapping(
      value = "/admin-elixir/blog-category-update/{id}/{lang}",
      method = RequestMethod.GET)
  public String updateCourse(
      @PathVariable("id") String id,
      @PathVariable("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminBlogService.updateViewBlogCategory(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("blogCategoryId", id);
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("blogCategoryDetails", payload.getResult());
          return "admin/admin-blog-category-details.html";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getMessage());
      //      return "aaaaa";
    }
    return null;
  }

  @RequestMapping(value = "/admin-elixir/do-blog-category-update", method = RequestMethod.POST)
  public String doUpdateBlogCategory(
      @ModelAttribute("blogCategoryUpdateRequest")
          BlogCategoryUpdateRequest blogCategoryUpdateRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminBlogService.doUpdateBlogCategory(blogCategoryUpdateRequest, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/blog-category-list";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getMessage());
      //      return "aaaaa";
    }
    return null;
  }

  @RequestMapping(value = "/admin-elixir/do-blog-category-add", method = RequestMethod.POST)
  public String doAddBlogCategory(
      @ModelAttribute("blogCategoryUpdateRequest")
          BlogCategoryUpdateRequest blogCategoryUpdateRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminBlogService.doAddBlogCategory(blogCategoryUpdateRequest, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/blog-category-list";
        } else {
          return "bbbbb";
        }
      } else {
        session.invalidate();
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      System.out.println("Error" + e.getMessage());
      //      return "aaaaa";
    }
    return null;
  }
}
