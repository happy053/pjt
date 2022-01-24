package market.feed;

import market.feed.model.FeedEntity;
import market.user.UserService;
import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/feed")
public class FeedController {

    @Autowired
    private FeedService service;
    @Autowired
    private UserService userService;

    @GetMapping("/write")
    public String write(Model model) {
        model.addAttribute("write", new FeedEntity());

        return "/feed/write";
    }

    @PostMapping("/write")
    public String write(FeedEntity write, Principal principal) {
        UserEntity param = new UserEntity();
        param.setId(principal.getName());
        write.setUserNum(userService.selUser(param).getUserNum());
        write.setId(principal.getName());

        service.insFeed(write);
        return "redirect:/user/main";
    }

    @GetMapping("/feedList")
    public String feedList(Model model, HttpServletRequest request) {
        List<FeedEntity> feedList = new ArrayList<FeedEntity>();
        int feedCount = service.feedCount();

        if(feedCount < 10) { // 피드글의 개수를 활용한 글목록 페이징 처리
            feedCount = 1;
        } else {
            ArrayList<Integer> list = new ArrayList<Integer>();
            feedCount = (feedCount / 10) + 1;
            for(int i=1; i<=feedCount; i++) {
                list.add(i);
            }
            model.addAttribute("feedCount", list);
        }

        if(request.getParameter("searchVal") != null) { // 글 검색시 보여줄 목록 생성
            feedList = service.search(request.getParameter("searchVal"), request.getParameter("searchCtnt"));
            model.addAttribute("feed", feedList);
            System.out.println(feedList);
        } else if(request.getParameter("feedCount") != null) { // 글목록 페이징 번호 클릭시 마다 보여줄 글(10개 단위)
            int count = Integer.parseInt(request.getParameter("feedCount"));
            if (count > 0) {
                feedList = service.selFeed((count - 1) * 10);
                model.addAttribute("feed", feedList);
            } else {
                feedList = service.selFeed(count);
                model.addAttribute("feed", feedList);
            }
        }
        return "/feed/feedList";
    }

    @PostMapping("/feedList")
    public String feedList(int a) {
        System.out.println(a);
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
        return "redirect:/feed/feedList?feedCount=1 ";
    }

    @GetMapping("/update")
    public String feedUpdate(@RequestParam int feedNum, Model model) {
        FeedEntity param = service.selDetail(feedNum);
        param.setFeedNum(feedNum);
        model.addAttribute("detail", param);
        return "/feed/update";
    }

    @PostMapping("/update")
    public String feedUpdate(FeedEntity param) {
        System.out.println(param.getFeedNum());
        System.out.println(param.getTitle());
        service.update(param);
        return "redirect:/feed/feedDetail?feedNum="+ param.getFeedNum();
    }


}
