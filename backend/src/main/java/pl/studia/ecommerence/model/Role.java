package pl.studia.ecommerence.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users;

    private String name;

    public Role(final String name) {
        this.name = name;
    }
}
