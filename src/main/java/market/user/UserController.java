package market.user;

import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/join")
    public String join(Model model) {
        model.addAttribute("user", new UserEntity());
        return "/user/join";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("param", new UserEntity());
        model.addAttribute("error", "ㅋㅋㅋㅋ");
        return "/user/login";
    }

    @GetMapping("/non")
    public String non() {
        return "/user/non";
    }

    @GetMapping("/main")
    public String main() {

        return "/user/main";
    }

    @PostMapping("/join")
    public String join(UserEntity param) {
        service.join(param);
        return "redirect:/user/main";
    }

//    @PostMapping("/login")
//    public String login(UserEntity param, HttpServletResponse response) throws Exception {
//        System.out.println("sdfskajflaskflasfsaf");
//        if(service.login(param) == false) {
//            response.setContentType("text/html; charset=UTF-8");
//            PrintWriter out = response.getWriter();
//            out.println("<script>alert('로그인 정보를 확인해주세요.'); history.go(-1);<script>");
//            out.flush();
//            return "redirect:/user/login";
//        } else {
//            return "/user/main";
//        }
//
//    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login";
    }

}
