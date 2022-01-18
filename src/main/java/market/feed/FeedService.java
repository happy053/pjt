package market.feed;

import market.feed.model.WriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedService {

    @Autowired
    private FeedMapper mapper;

    public int insFeed(WriteEntity write) {
        return mapper.insFeed(write);
    }

    public ArrayList<WriteEntity> selFeed(int count) {
        return mapper.selFeed(count);
    }

    public WriteEntity selDetail(int feedNum) {
        return mapper.selDetail(feedNum);
    }

    public int delFeed(int feedNum) {
        return mapper.delFeed(feedNum);
    }

    public int update(WriteEntity param) {
        return mapper.update(param);
    }
    public int feedCount() { return mapper.feedCount(); }

}
