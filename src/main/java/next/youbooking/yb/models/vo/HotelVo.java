package next.youbooking.yb.models.vo;

import java.util.List;

public class HotelVo {
    private String name;
    private String description;
    private AddressVo address;
    private AttachmentVo attachment;
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

    public AttachmentVo getAttachment() {
        return attachment;
    }

    public void setAttachment(AttachmentVo attachment) {
        this.attachment = attachment;
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
                ", attachment=" + attachment +
                ", bedRooms=" + bedRooms +
                '}';
    }
}
