package market.feed.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FileEntity {
    private String uuid;
    private String fileName;
    private String contentType;

    public FileEntity(String uuid, String fileName, String contentType) {
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        System.out.println(contentType);
    }
}
