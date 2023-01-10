package next.youbooking.yb.models.vo;

public class BedRoomVo {

    private double price;
    private String name;
    private int numberOfBeds;
    private String typeRoom;

    public BedRoomVo() {
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    public void setNumberOfBeds(int numberOfBeds) {
        this.numberOfBeds = numberOfBeds;
    }

    public String getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(String typeRoom) {
        this.typeRoom = typeRoom;
    }

    @Override
    public String toString() {
        return "BedRoomVo{" +
                "price=" + price +
                ", name='" + name + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", typeRoom='" + typeRoom + '\'' +
                '}';
    }
}
