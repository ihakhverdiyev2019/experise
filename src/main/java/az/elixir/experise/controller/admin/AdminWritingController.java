package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.writing.WritingUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminWritingService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminWritingController {

  @Autowired private AdminWritingService adminWritingService;
  @Autowired private UserService userService;
  @Autowired private LanguageService languageService;

  @RequestMapping(value = "/admin-elixir/writing", method = RequestMethod.GET)
  public String listOfWriting(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminWritingService.writingList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          System.out.println(payload.getResult());
          model.addAttribute("writings", payload.getResult());
          return "admin/admin-writings.html";
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

  @RequestMapping(value = "/admin-elixir/add-writing", method = RequestMethod.GET)
  public String addWriting(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", "EN");
          return "admin/admin-writing-add.html";
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

  @RequestMapping(value = "/admin-elixir/writing-remove/{id}", method = RequestMethod.GET)
  public String removeWriting(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminWritingService.removeWriting(id);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/writing";
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

  @RequestMapping(value = "/admin-elixir/writing-update/{id}/{lang}", method = RequestMethod.GET)
  public String updateWriting(
      @PathVariable("id") String id,
      @PathVariable("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminWritingService.updateViewWriting(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("id", id);
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("details", payload.getResult());
          return "admin/admin-writing-details.html";
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

  @RequestMapping(value = "/admin-elixir/do-writing-update", method = RequestMethod.POST)
  public String doUpdateWriting(
      @ModelAttribute("request") WritingUpdateRequest writingUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminWritingService.doUpdateWriting(writingUpdateRequest, file, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/writing";
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

  @RequestMapping(value = "/admin-elixir/do-writing-add", method = RequestMethod.POST)
  public String doAddWriting(
      @ModelAttribute("request") WritingUpdateRequest writingUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminWritingService.doAddWriting(writingUpdateRequest, file, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/writing";
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
