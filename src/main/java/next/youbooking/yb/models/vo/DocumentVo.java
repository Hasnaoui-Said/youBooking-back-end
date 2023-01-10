package next.youbooking.yb.models.vo;

public class DocumentVo {
    private String type;

    public DocumentVo() {
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "DocumentVo{" +
                "type='" + type + '\'' +
                '}';
    }
}
