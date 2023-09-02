package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.degree.DegreeUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminDegreeService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminDegreeController {

  @Autowired private AdminDegreeService adminDegreeService;
  @Autowired private UserService userService;
  @Autowired private LanguageService languageService;

  @RequestMapping(value = "/admin-elixir/degree", method = RequestMethod.GET)
  public String listOfDegree(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminDegreeService.degreeList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          System.out.println(payload.getResult());
          model.addAttribute("degrees", payload.getResult());
          return "admin/admin-degrees.html";
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

  @RequestMapping(value = "/admin-elixir/add-degree", method = RequestMethod.GET)
  public String addDegree(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("degreesCategory", adminDegreeService.getCategoryByLang("EN"));
          model.addAttribute("currentLang", "EN");
          return "admin/admin-degree-add.html";
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

  @RequestMapping(value = "/admin-elixir/degree-remove/{id}", method = RequestMethod.GET)
  public String removeDegree(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminDegreeService.removeDegree(id);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/degree";
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

  @RequestMapping(value = "/admin-elixir/degree-update/{id}/{lang}", method = RequestMethod.GET)
  public String updateDegree(
      @PathVariable("id") String id,
      @PathVariable("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminDegreeService.updateViewDegree(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("id", id);
          model.addAttribute("degreesCategory", adminDegreeService.getCategoryByLang("EN"));
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("details", payload.getResult());
          return "admin/admin-degree-details.html";
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

  @RequestMapping(value = "/admin-elixir/do-degree-update", method = RequestMethod.POST)
  public String doUpdateDegree(
      @ModelAttribute("request") DegreeUpdateRequest degreeUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      @RequestParam("imageFront") MultipartFile fileFront,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminDegreeService.doUpdateDegree(degreeUpdateRequest, file, fileFront, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/degree";
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

  @RequestMapping(value = "/admin-elixir/do-degree-add", method = RequestMethod.POST)
  public String doAddDegree(
      @ModelAttribute("request") DegreeUpdateRequest degreeUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      @RequestParam("imageFront") MultipartFile fileFront,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        System.out.println("Degree Category: " + degreeUpdateRequest.getCategoryKey());
        Payload payload =
            adminDegreeService.doAddDegree(degreeUpdateRequest, file, fileFront, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/degree";
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
