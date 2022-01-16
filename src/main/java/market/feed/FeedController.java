package market.feed;

import market.feed.model.WriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService service;

    @GetMapping("/write")
    public String write(Principal principal, Model model) {
        model.addAttribute("write", new WriteEntity());
        if(principal != null) {
            System.out.println(principal.getName());
        }
        return "/feed/write";
    }

    @PostMapping
    public int write(WriteEntity write) {
        return service.insFeed(write);
    }
}
