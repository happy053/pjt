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

    @GetMapping("join")
    public String moveJoin(Model model) {
        UserEntity user = new UserEntity();
        model.addAttribute("user", user);
        return "join";
    }

    @ResponseBody
    @PostMapping("join")
    public int join(UserEntity user, Model model) {
        if(service.signUp(user) == 1) {
            model.addAttribute("msg", "이미 가입된 이메일");
            model.addAttribute("href", "/join");
        } else if(service.signUp(user) == 2) {
            model.addAttribute("msg", "비밀번호 확인");
            model.addAttribute("href", "/join");
        } else {
            model.addAttribute("msg", "회원가입 완료");
            model.addAttribute("href", "/main");
            return service.signUp(user);
        }
        return 0;
    }
}
