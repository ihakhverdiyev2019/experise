package az.elixir.experise.controller.admin;

import az.elixir.experise.dto.admin.Payload;
import az.elixir.experise.dto.admin.scholarship.ScholarShipUpdateRequest;
import az.elixir.experise.model.UserEntity;
import az.elixir.experise.utils.SaveImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;

@RestController
public class TestController {

    @Autowired
    private SaveImage saveImage;

    @RequestMapping(value = "/test-file", method = RequestMethod.POST)
    public String testFile(
            @RequestParam("image") MultipartFile file) {
       saveImage.savePhoto("a", file);
        return "DONE";
    }
}
