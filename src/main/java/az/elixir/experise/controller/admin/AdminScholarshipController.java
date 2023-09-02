package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.scholarship.ScholarShipUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminScholarshipService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminScholarshipController {

  @Autowired private AdminScholarshipService adminScholarshipService;
  @Autowired private UserService userService;
  @Autowired private LanguageService languageService;

  @RequestMapping(value = "/admin-elixir/scholarship", method = RequestMethod.GET)
  public String listOfScholarship(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminScholarshipService.shcolarshipList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          System.out.println(payload.getResult());
          model.addAttribute("scholarships", payload.getResult());
          return "admin/admin-scholarships.html";
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

  @RequestMapping(value = "/admin-elixir/add-scholarship", method = RequestMethod.GET)
  public String addScholarship(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", "EN");
          return "admin/admin-scholarship-add.html";
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

  @RequestMapping(value = "/admin-elixir/scholarship-remove/{id}", method = RequestMethod.GET)
  public String removeScholarship(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminScholarshipService.removeScholarship(id);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/scholarship";
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
      value = "/admin-elixir/scholarship-update/{id}/{lang}",
      method = RequestMethod.GET)
  public String updateScholarship(
      @PathVariable("id") String id,
      @PathVariable("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminScholarshipService.updateViewScholarship(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("id", id);
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("details", payload.getResult());
          return "admin/admin-scholarship-details.html";
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

  @RequestMapping(value = "/admin-elixir/do-scholarship-update", method = RequestMethod.POST)
  public String doUpdateScholarship(
      @ModelAttribute("request") ScholarShipUpdateRequest scholarShipUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminScholarshipService.doUpdateScholarship(scholarShipUpdateRequest, file, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/scholarship";
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

  @RequestMapping(value = "/admin-elixir/do-scholarship-add", method = RequestMethod.POST)
  public String doAddScholarship(
      @ModelAttribute("request") ScholarShipUpdateRequest scholarShipUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminScholarshipService.doAddScholarship(scholarShipUpdateRequest, file, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/scholarship";
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
