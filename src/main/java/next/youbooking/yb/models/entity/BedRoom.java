package next.youbooking.yb.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import next.youbooking.yb.models.enums.StatusRoom;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "bedRoom")
public class BedRoom implements Serializable {

    @Id
    @Column(unique = true, name = "uuid")
    private UUID uuid;
    private double price;
    private String name;
    private int numberOfBeds;
    @ManyToOne
    private TypeRoom typeRoom;
    @Enumerated(EnumType.STRING)
    private StatusRoom statusRoom;
    @OneToMany(mappedBy = "bedroom")
    private List<Reservation> reservations;
    @ManyToOne
    private Hotel hotel;

    public BedRoom() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
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

    public TypeRoom getTypeRoom() {
        return typeRoom;
    }

    public void setTypeRoom(TypeRoom typeRoom) {
        this.typeRoom = typeRoom;
    }

    public StatusRoom getStatusRoom() {
        return statusRoom;
    }

    public void setStatusRoom(StatusRoom statusRoom) {
        this.statusRoom = statusRoom;
    }

    @JsonIgnore
    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @JsonIgnore
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    @Override
    public String toString() {
        return "BedRoom{" +
                "uuid=" + uuid +
                ", price=" + price +
                ", name='" + name + '\'' +
                ", numberOfBeds=" + numberOfBeds +
                ", typeRoom=" + typeRoom +
                ", statusRoom=" + statusRoom +
                '}';
    }
}
