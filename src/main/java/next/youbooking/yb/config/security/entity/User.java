package next.youbooking.yb.config.security.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "users")
@Data @NoArgsConstructor
public class User implements Serializable {
    private UUID uuid;
    @Id
    @Column(unique = true, name = "username")
    @Size(min = 4, max = 20)
    private String username;

    @Column(unique = true)
    private String email;
    //    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    private String phone;

    @CreatedDate
    protected Date createdAt;

    @LastModifiedDate
    protected Date updatedAt;
    private boolean isActive;
    @OneToMany(mappedBy = "username", fetch = FetchType.EAGER)
    private List<UsersRoles> roles;
}
