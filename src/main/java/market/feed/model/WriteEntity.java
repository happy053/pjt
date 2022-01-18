package market.feed.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteEntity {
    private int feedNum;
    private String id;
    private String title;
    private String contents;
    private int sellNbuy;
    private int userNum;
    private String ymd;
    private int count;
}
