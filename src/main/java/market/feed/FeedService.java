package market.feed;

import market.feed.model.WriteEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedService {

    @Autowired
    private FeedMapper mapper;

    public int insFeed(WriteEntity write) {
        return mapper.insFeed(write);
    }

}
