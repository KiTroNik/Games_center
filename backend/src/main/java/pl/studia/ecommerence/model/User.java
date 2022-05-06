package pl.studia.ecommerence.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Collection;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity(name = "User")
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(name = "user_email_unique", columnNames = {"email"})
})
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "first name cant be null.")
    @Size(min = 2, message = "First Name can't be less than 2 characters.")
    private String firstName;

    @NotEmpty(message = "Last name cant be null.")
    @Size(min = 2, message = "Last Name can't be less than 2 characters.")
    private String lastName;

    @Email(message = "Email should be valid.")
    @NotEmpty(message = "Email can't be null")
    private String email;

    @NotNull(message = "Password can't be null")
    @Size(min = 8, message = "Password must be more than 8 characters.")
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Collection<Role> roles;
}
