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
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


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
    public void join(UserEntity param, Model model, HttpServletResponse response) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        int error = service.join(param);
        if (error == 2) {
            out.println("<script charset='utf-8' language='javascript'> alert('비밀번호는 6자 이상이어야 합니다.'); " +
                    "location.href='join' </script>");
            out.flush();
        } else if (error == 3) {
            model.addAttribute("error", "비밀번호가 일치하지 않습니다.");
            out.println("<script charset='utf-8' language='javascript'> alert('비밀번호가 일치하지 않습니다.'); " +
                    "location.href='join' </script>");
            out.flush();
        } else if (error == 4) {
            out.println("<script charset='utf-8' language='javascript'> alert('아이디를 입력해주세요.'); " +
                    "location.href='join' </script>");
            out.flush();;
        } else {
            out.println("<script charset='utf-8' language='javascript'> alert('회원가입에 성공했습니다.'); " +
                    "location.href='login' </script>");
            out.flush();;
        }
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

    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/user/login";
    }

}
