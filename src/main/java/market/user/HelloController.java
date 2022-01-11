package market.user;

import market.user.model.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class HelloController {

    @GetMapping("/")
    public String mainHome(Model model){
        model.addAttribute("hello", "thymeleaf");
        return "main";
    }

    @GetMapping("join")
    public String moveJoin(Model model) {
        model.addAttribute("user", "UserEntity");
        return "join";
    }

    @PostMapping("join")
    public void join(UserEntity user) {

    }

}
