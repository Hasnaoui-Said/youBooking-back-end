package next.youbooking.yb.models.entity;

import next.youbooking.yb.models.enums.StatusOffer;

import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "offer")
public class Offer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    private String title;
    private String description;
    private String sub_description;
    @Enumerated(EnumType.STRING)
    private StatusOffer status;
    @ManyToOne
    private Hotel hotel;

    public Offer() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getSub_description() {
        return sub_description;
    }

    public void setSub_description(String sub_description) {
        this.sub_description = sub_description;
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

    public StatusOffer getStatus() {
        return status;
    }

    public void setStatus(StatusOffer status) {
        this.status = status;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "uuid=" + uuid +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", sub_description='" + sub_description + '\'' +
                ", status=" + status +
                ", hotel=" + hotel +
                '}';
    }
}
