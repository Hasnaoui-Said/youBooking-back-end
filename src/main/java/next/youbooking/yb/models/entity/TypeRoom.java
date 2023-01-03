package next.youbooking.yb.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "typeRoom")
public class TypeRoom implements Serializable {

    @Id
    @Column(unique = true, name = "uuid")
    private UUID uuid;
    private String name;
    private String description;
    @OneToMany(mappedBy = "typeRoom")
    private List<BedRoom> bedRooms;

    public TypeRoom() {
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
    public List<BedRoom> getBedRooms() {
        return bedRooms;
    }

    public void setBedRooms(List<BedRoom> bedRooms) {
        this.bedRooms = bedRooms;
    }

    @Override
    public String toString() {
        return "TypeRoom{" +
                "uuid=" + uuid +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
