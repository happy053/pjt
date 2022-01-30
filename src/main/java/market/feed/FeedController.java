package market.feed;

import market.feed.model.FeedEntity;
import market.user.UserService;
import market.user.model.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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
    public String write(FeedEntity write, Principal principal, MultipartHttpServletRequest mRequest) {
        List<MultipartFile> fileList = mRequest.getFiles("file");
        String src = mRequest.getParameter("src");

        String path = "C:\\image\\";

        for(MultipartFile mf : fileList) {
            String originFileName = mf.getOriginalFilename();
            long fileSize = mf.getSize();

            String safeFile = path + originFileName;
            try {
                mf.transferTo(new File(safeFile));
            } catch(IllegalStateException e) {
                e.printStackTrace();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }

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
        int feedCount = service.feedCount(); // 피드글의 개수를 활용한 글목록 페이징 처리
        ArrayList<Integer> list = new ArrayList<Integer>();

        if(request.getParameter("searchVal") != null) { // 글 검색시 보여줄 목록 생성
            String value = request.getParameter("searchVal");
            String ctnt = request.getParameter("searchCtnt");
            model.addAttribute("value", value);
            model.addAttribute("ctnt", ctnt);
            int searchNum = service.selSearch(value, ctnt);
            feedList = service.search(value, ctnt);
            if(searchNum < 10) {
                feedCount = 1;
            } else {
                if(request.getParameter("feedSearch") != null) {
                    String searchCount = request.getParameter("feedSearch");
                    int searchCountNum = Integer.parseInt(searchCount);
                    System.out.println(searchCountNum);
                    feedList = service.searchL(((searchCountNum-1) * 10), value, ctnt);
                }
                if(searchNum % 10 == 0) {
                    feedCount = (searchNum / 10);
                } else {
                    feedCount = (searchNum / 10) + 1;
                }
                for(int i=1; i<=feedCount; i++) {
                    list.add(i);
                }
                model.addAttribute("feedSearchCount", list);
            }
            model.addAttribute("feed", feedList);
        } else if(request.getParameter("feedCount") != null) { // 글목록 페이징 번호 클릭시 마다 보여줄 글(10개 단위)
            int count = Integer.parseInt(request.getParameter("feedCount"));
            if (count > 0) {
                feedList = service.selFeed((count - 1) * 10);
                model.addAttribute("feed", feedList);
            } else {
                feedList = service.selFeed(count);
                model.addAttribute("feed", feedList);
            }

            if(feedCount < 10) {
                feedCount = 1;
            } else {
                if(feedCount % 10 == 0) {
                    feedCount = (feedCount / 10);
                } else {
                    feedCount = (feedCount / 10) + 1;
                }
                for(int i=1; i<=feedCount; i++) {
                    list.add(i);
                }
                model.addAttribute("feedCount", list);
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
