package next.youbooking.yb.models.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import next.youbooking.yb.security.models.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "reservation")
public class Reservation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID uuid;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh-mm-ss")
    private Date startDate;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh-mm-ss")
    private Date endDate;
    private double rate;

    @ManyToOne
    private User guest;
    @ManyToOne
    private BedRoom bedroom;

    public Reservation() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public User getUser() {
        return guest;
    }

    public void setUser(User user) {
        this.guest = user;
    }

    public BedRoom getBedRoom() {
        return bedroom;
    }

    public void setBedRoom(BedRoom bedRoom) {
        this.bedroom = bedRoom;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "uuid=" + uuid +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", rate=" + rate +
                ", guest=" + guest +
                ", bedroom=" + bedroom +
                '}';
    }
}
