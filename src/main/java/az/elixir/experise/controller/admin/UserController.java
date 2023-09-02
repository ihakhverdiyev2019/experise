package az.elixir.experise.controller.admin;

<<<<<<< Updated upstream
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

=======
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.UserLoginRequest;
>>>>>>> Stashed changes
import az.elixir.experise.service.admin.UserService;

@Controller
public class UserController {

  @Autowired private UserService userService;
<<<<<<< Updated upstream

  @RequestMapping(value = "/admin-elixir", method = RequestMethod.GET)
  public String adminDashboard() {
    return "admin/auth-login.html";
=======

  @RequestMapping(value = "/admin-elixir", method = RequestMethod.GET)
  public String adminlogin() {
    return "admin/auth-login.html";
  }

  @RequestMapping(value = "/admin-elixir/welcome", method = RequestMethod.GET)
  public String adminDashboard(HttpSession session) {
    try {
      if (session != null || session.getAttribute("user") != null) {
        return "admin/admin-dashboard.html";
      } else {
        return "redirect:/admin-elixir";
      }
    } catch (Exception e) {
      return "";
    }
  }

  @RequestMapping(value = "/admin-elixir/login", method = RequestMethod.POST)
  public String login(
      @ModelAttribute("userForm") UserLoginRequest userLoginRequest, HttpSession session) {
    try {
      Payload payload = userService.login(userLoginRequest);
      if (payload.isSuccess()) {
        session.setAttribute("user", payload.getResult());
        return "redirect:/admin-elixir/welcome";
      } else {
        return "";
      }
    } catch (Exception e) {
      return "";
    }
>>>>>>> Stashed changes
  }
}
