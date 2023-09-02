package az.elixir.experise.controller.admin;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.course.CoursesUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.service.admin.AdminCourseService;
import az.elixir.experise.service.admin.LanguageService;
import az.elixir.experise.service.admin.UserService;

@Controller
public class AdminCourseController {

  @Autowired private AdminCourseService adminCourseService;
  @Autowired private UserService userService;
  @Autowired private LanguageService languageService;

  @RequestMapping(value = "/admin-elixir/courses", method = RequestMethod.GET)
  public String listOfCourse(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminCourseService.coursesList();
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          System.out.println(payload.getResult());
          model.addAttribute("courses", payload.getResult());
          return "admin/admin-courses.html";
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

  @RequestMapping(value = "/admin-elixir/add-course", method = RequestMethod.GET)
  public String addCourse(HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        if (userService.checkUser(userEntity)) {
          model.addAttribute("currentLang", "EN");
          return "admin/admin-course-add.html";
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

  @RequestMapping(value = "/admin-elixir/course-remove/{id}", method = RequestMethod.GET)
  public String removeCourse(@PathVariable("id") String id, HttpSession session, Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminCourseService.removecourse(id);
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

  @RequestMapping(value = "/admin-elixir/course-update/{id}/{lang}", method = RequestMethod.GET)
  public String updateCourse(
      @PathVariable("id") String id,
      @PathVariable("lang") String lang,
      HttpSession session,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminCourseService.updateViewCourse(id, lang);
        if (payload.isSuccess() & userService.checkUser(userEntity)) {
          model.addAttribute("courseId", id);
          model.addAttribute("currentLang", lang);
          model.addAttribute("languages", languageService.findAllLanguage());
          model.addAttribute("courseDetails", payload.getResult());
          return "admin/admin-course-details.html";
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

  @RequestMapping(value = "/admin-elixir/do-course-update", method = RequestMethod.POST)
  public String doUpdateCourse(
      @ModelAttribute("coursesUpdateRequest") CoursesUpdateRequest coursesUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload =
            adminCourseService.doUpdateCourses(coursesUpdateRequest, file, userEntity);
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

  @RequestMapping(value = "/admin-elixir/do-course-add", method = RequestMethod.POST)
  public String doAddCourse(
      @ModelAttribute("coursesUpdateRequest") CoursesUpdateRequest coursesUpdateRequest,
      HttpSession session,
      @RequestParam("image") MultipartFile file,
      Model model) {
    try {
      if (((UserEntity) session.getAttribute("user")) != null) {
        UserEntity userEntity = ((UserEntity) session.getAttribute("user"));
        Payload payload = adminCourseService.doAddCourses(coursesUpdateRequest, file, userEntity);
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
}
