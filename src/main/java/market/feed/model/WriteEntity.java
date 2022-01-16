package market.feed.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WriteEntity {
    private String title;
    private String contents;
    private int sellNbuy;
    private int userNum;
}
