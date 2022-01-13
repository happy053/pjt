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


    @Autowired private UserService service;

    @GetMapping("/")
    public String mainHome(Model model){
        model.addAttribute("hello", "thymeleaf");
        return "main";
    }

    @GetMapping("signUp")
    public String signUp(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signUp";
    }

    @ResponseBody
    @PostMapping("signUp")
    public int signUp(UserEntity user) {
//        if(service.signUp(user) == 1 || service.signUp(user) == 2) {
//            System.out.println("No");
//        }
        return service.signUp(user);
    }

    @GetMapping("signIn")
    public String signIn(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "signIn";
    }

    @ResponseBody
    @PostMapping
    public String signIn(UserEntity user) {
        return service.signIn(user);
    }
}
