package litte.example.demo.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity(name = "my_user")
public class User {

    @ManyToMany
    @JoinTable(name="my_role_mapping")
    private Set<Role> roles;

    @Id
    private UUID uuid = UUID.randomUUID();

    private Date created = new Date();

    @NotNull
    @Column(unique = true)
    private String name;

    private String comment;

    public User(@NotNull String name) {
        this.name = name;
    }

    private User() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
