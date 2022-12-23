package az.elixir.experise.controller.admin;

import az.elixir.experise.dto.website.AboutView;
import az.elixir.experise.service.admin.UserService;
import az.elixir.experise.service.website.CoursesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/admin-elixir", method = RequestMethod.GET)
    public String adminDashboard() {
        return "admin/auth-login.html";
    }
}
