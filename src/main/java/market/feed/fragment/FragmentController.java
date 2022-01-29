package market.feed.fragment;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/fragment")
public class FragmentController {

    @GetMapping("header")
    public void loginInfo(Principal principal, Model model) {
        String user = principal.getName();
        model.addAttribute("user", user);
        System.out.println(user);
    }
}
