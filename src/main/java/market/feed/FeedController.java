package market.feed;

import market.feed.model.WriteEntity;
import market.user.UserService;
import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService service;
    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String write(Model model) {

        model.addAttribute("write", new WriteEntity());

        return "/feed/write";
    }

    @PostMapping("/write")
    public String write(WriteEntity write, Principal principal) {
        UserEntity param = new UserEntity();
        param.setId(principal.getName());
        write.setUserNum(userService.selUser(param).getUserNum());
        write.setId(principal.getName());

        service.insFeed(write);
        return "redirect:/user/main";
    }

    @GetMapping("/feedList")
    public String feedList(Model model) {
        List<WriteEntity> feedList = service.selFeed();
        model.addAttribute("feed", feedList);
        return "/feed/feedList";
    }

    @GetMapping("/feedDetail")
    public String feedDetail(@RequestParam int feedNum, Model model) {
        model.addAttribute("feed", service.selDetail(feedNum));
        return "/feed/feedDetail";
    }

    @GetMapping("/feedDelete")
    public String feedDelete(@RequestParam int feedNum) {
        service.delFeed(feedNum);
        return "redirect:/feed/feedList";
    }

    @GetMapping("/update")
    public String feedUpdate(@RequestParam int feedNum, Model model) {
        WriteEntity param = service.selDetail(feedNum);
        param.setFeedNum(feedNum);
        model.addAttribute("detail", param);
        return "/feed/update";
    }

    @PostMapping("/update")
    public String feedUpdate(WriteEntity param) {
        System.out.println(param.getFeedNum());
        System.out.println(param.getTitle());
        service.update(param);
        return "/feed/feedDetail?"+ param.getFeedNum();
    }
}
