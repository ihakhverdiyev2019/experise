package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.about.AboutUpdateRequest;
import az.elixir.experise.dto.admin.seo.SeoRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminAboutService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminAboutController {

  @Autowired private UserService userService;

  @Autowired private LanguageService languageService;

  @Autowired private AdminAboutService adminAboutService;

  @RequestMapping(value = "/admin-elixir/about-update/{lang}", method = RequestMethod.GET)
  public String updateAbout(@PathVariable("lang") String lang, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminAboutService.updateViewAbout(lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("aboutDetails", payload.getResult());
          return "admin/admin-about.html";
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

  @RequestMapping(value = "/admin-elixir/do-about-update", method = RequestMethod.POST)
  public String doUpdateAbout(
      @ModelAttribute("aboutUpdateRequest") AboutUpdateRequest aboutUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminAboutService.doUpdateAbout(aboutUpdateRequest, file, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/faq";
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

  @RequestMapping(value = "/admin-elixir/about-seo-update/{lang}", method = RequestMethod.GET)
  public String updateAboutSeo(
      @PathVariable("lang") String lang, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminAboutService.updateSeoViewAbout(lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("aboutSeo", payload.getResult());
          return "admin/admin-about-seo.html";
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
    return "";
  }

  @RequestMapping(value = "/admin-elixir/do-about-seo-update", method = RequestMethod.POST)
  public String doUpdateAboutSeo(
      @ModelAttribute("aboutSeoUpdateRequest") SeoRequest seoRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminAboutService.doSeoUpdateFAQ(seoRequest, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/about-update/EN";
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
