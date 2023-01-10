package next.youbooking.yb.models.vo;

import java.util.List;

public class AttachmentVo {
    private String title;
    private String description;
    private List<DocumentVo> documents;

    public AttachmentVo() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentVo> getDocumentVos() {
        return documents;
    }

    public void setDocumentVos(List<DocumentVo> documentVos) {
        this.documents = documentVos;
    }

    @Override
    public String toString() {
        return "AttachmentVo{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", documentVos=" + documents +
                '}';
    }
}
