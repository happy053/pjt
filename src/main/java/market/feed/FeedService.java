package market.feed;

import market.feed.model.FeedEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class FeedService {

    @Autowired
    private FeedMapper mapper;

    public int insFeed(FeedEntity write) {
        return mapper.insFeed(write);
    }

    public ArrayList<FeedEntity> selFeed(int count) {
        return mapper.selFeed(count);
    }

    public FeedEntity selDetail(int feedNum) {
        return mapper.selDetail(feedNum);
    }

    public int delFeed(int feedNum) {
        return mapper.delFeed(feedNum);
    }

    public int update(FeedEntity param) {
        return mapper.update(param);
    }
    public int feedCount() { return mapper.feedCount(); }

    public ArrayList<FeedEntity> search(String value, String ctnt) { return mapper.search(value, ctnt); }
    public ArrayList<FeedEntity> searchL(int count, String value, String ctnt) { return mapper.searchL(count, value, ctnt); }
    public int selSearch(String value, String ctnt) { return mapper.selSearch(value, ctnt); }

    public int insImg(String imgName, int feedNum) { return mapper.insImg(imgName, feedNum); }
    public int selFeedNum(FeedEntity param) { return mapper.selFeedNum(param); }


}
