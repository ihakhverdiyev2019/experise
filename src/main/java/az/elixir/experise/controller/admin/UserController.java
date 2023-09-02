package az.elixir.experise.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import az.elixir.experise.service.admin.UserService;

@Controller
public class UserController {

  @Autowired private UserService userService;

  @RequestMapping(value = "/admin-elixir", method = RequestMethod.GET)
  public String adminDashboard() {
    return "admin/auth-login.html";
  }
}
