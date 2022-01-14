package market.user;

import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {

    @Autowired
    private UserService service;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("param", new UserEntity());
        return "/login";
    }

    @GetMapping("/non")
    public String non() {
        return "/non";
    }

    @GetMapping("/")
    public String main() {
        return "/main";
    }

    @PostMapping("/join")
    public String join(UserEntity param) {
        service.join(param);
        return "redirect:main";
    }

    @PostMapping("/login")
    public String login(UserEntity param) {
        if(service.login(param) == true) {
            return "/";
        } else {
            return "redirect:non";
        }
    }
}
