package next.youbooking.yb.models.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name = "document")
public class Document implements Serializable {

    @Id
    @Column(unique = true, name = "uuid")
    private UUID uuid;
    private String type;
    private byte[] image;
    @ManyToOne
    private Attachment attachment;

    public Document() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @JsonIgnore
    public Attachment getAttachment() {
        return attachment;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setAttachment(Attachment attachment) {
        this.attachment = attachment;
    }

    @Override
    public String toString() {
        return "Document{" +
                "uuid=" + uuid +
                ", type='" + type + '\'' +
                ", image=" + Arrays.toString(image) +
                '}';
    }
}
