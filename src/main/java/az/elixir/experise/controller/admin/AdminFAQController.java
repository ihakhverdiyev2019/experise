package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.faq.FAQUpdateRequest;
import az.elixir.experise.dto.admin.seo.SeoRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminFAQService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminFAQController {
  @Autowired private AdminFAQService faqService;
  @Autowired private UserService userService;

  @Autowired private LanguageService languageService;

  @RequestMapping(value = "/admin-elixir/add-faq", method = RequestMethod.GET)
  public String addCourse(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", "EN");
          return "admin/admin-faq-add.html";
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

  @RequestMapping(value = "/admin-elixir/do-faq-add", method = RequestMethod.POST)
  public String doAddCourse(
      @ModelAttribute("request") FAQUpdateRequest faqUpdateRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.doAddFAQ(faqUpdateRequest, userEntity);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          return "redirect:/admin-elixir/courses";
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

  @RequestMapping(value = "/admin-elixir/faq", method = RequestMethod.GET)
  public String listOfFaq(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.faqList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("faqs", payload.getResult());
          return "admin/admin-faq.html";
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

  @RequestMapping(value = "/admin-elixir/faq-remove/{id}", method = RequestMethod.GET)
  public String removeFAQ(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.removeFAQ(id);
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

  @RequestMapping(value = "/admin-elixir/faq-update/{id}", method = RequestMethod.GET)
  public String updateFAQ(
      @PathVariable("id") String id,
      @RequestParam("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.updateViewFAQ(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("faqId", id);
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("faqDetails", payload.getResult());
          return "admin/admin-faq-details.html";
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

  @RequestMapping(value = "/admin-elixir/do-faq-update", method = RequestMethod.POST)
  public String doUpdateFAQ(
      @ModelAttribute("faqUpdateRequest") FAQUpdateRequest faqUpdateRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.doUpdateFAQ(faqUpdateRequest, userEntity);
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

  @RequestMapping(value = "/admin-elixir/faq-seo-update", method = RequestMethod.GET)
  public String updateFAQSeo(@RequestParam("lang") String lang, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.updateSeoViewFAQ(lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("faqSeo", payload.getResult());
          return "admin/admin-faq-seo.html";
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

  @RequestMapping(value = "/admin-elixir/do-faq-seo-update", method = RequestMethod.POST)
  public String doUpdateFAQSeo(
      @ModelAttribute("faqSeoUpdateRequest") SeoRequest seoRequest,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = faqService.doSeoUpdateFAQ(seoRequest, userEntity);
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
}
