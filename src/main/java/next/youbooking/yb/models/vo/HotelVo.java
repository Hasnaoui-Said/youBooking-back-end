package next.youbooking.yb.models.vo;

import java.util.List;

public class HotelVo {
    private String name;
    private String description;
    private AddressVo address;
    private List<AttachmentVo> attachments;
    private List<BedRoomVo> bedRooms;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public AddressVo getAddress() {
        return address;
    }

    public void setAddress(AddressVo address) {
        this.address = address;
    }

    public List<AttachmentVo> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentVo> attachments) {
        this.attachments = attachments;
    }

    public List<BedRoomVo> getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(List<BedRoomVo> bedRooms) {
        this.bedRooms = bedRooms;
    }

    public HotelVo() {
    }

    @Override
    public String toString() {
        return "HotelVo{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", address=" + address +
                ", attachments=" + attachments +
                ", bedRooms=" + bedRooms +
                '}';
    }
}
