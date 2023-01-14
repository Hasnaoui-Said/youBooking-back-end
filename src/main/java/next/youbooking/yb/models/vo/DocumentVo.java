package next.youbooking.yb.models.vo;

import org.springframework.web.multipart.MultipartFile;

public class DocumentVo {
    private MultipartFile image;

    public DocumentVo() {
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
