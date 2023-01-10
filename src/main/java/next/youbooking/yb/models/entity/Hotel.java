package next.youbooking.yb.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import next.youbooking.yb.models.enums.StateHotel;
import next.youbooking.yb.security.models.entity.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "hotel")
public class Hotel implements Serializable {

    @Id
    @Column(unique = true, name = "uuid")
    private UUID uuid;
    private String name;
    private String description;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_uuid", referencedColumnName = "uuid")
    private Address address;

    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Attachment> attachments;
    @OneToMany(mappedBy = "hotel", cascade = {CascadeType.ALL, CascadeType.PERSIST})
    private List<BedRoom> bedRooms;
    @OneToMany(mappedBy = "hotel", cascade = CascadeType.ALL)
    private List<Offer> offers;
    @Enumerated(EnumType.STRING)
    private StateHotel stateHotel;

    public Hotel() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

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

    @JsonIgnore
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<Attachment> attachments) {
        this.attachments = attachments;
    }

    public List<BedRoom> getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(List<BedRoom> bedRooms) {
        this.bedRooms = bedRooms;
    }

    @JsonIgnore
    public List<Offer> getOffers() {
        return offers;
    }

    public void setOffers(List<Offer> offers) {
        this.offers = offers;
    }

    public StateHotel getStateHotel() {
        return stateHotel;
    }

    public void setStateHotel(StateHotel stateHotel) {
        this.stateHotel = stateHotel;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", user=" + user +
                ", address=" + address +
                ", attachments=" + attachments +
                ", bedRooms=" + bedRooms +
                ", stateHotel=" + stateHotel +
                '}';
    }
}
