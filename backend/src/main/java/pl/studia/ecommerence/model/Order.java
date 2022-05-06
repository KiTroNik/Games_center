package pl.studia.ecommerence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.json.JSONPropertyIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@JsonIgnoreProperties(value={"handler", "hibernateLazyInitializer", "FieldHandler"})
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "created_date")
    private Date createdDate;

    @Column(name = "total_price")
    private Double totalPrice;

    @OneToMany(mappedBy = "order")
    @ToString.Exclude
    private List<OrderItem> orderItems;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @Pattern(message = "Enter a valid phone number", regexp = "(?<!\\w)(\\(?(\\+|00)?48\\)?)?[ -]?\\d{3}[ -]?\\d{3}[ -]?\\d{3}(?!\\w)")
    @NotEmpty(message = "Phone number can't be Empty")
    private String phoneNumber;

    @NotEmpty(message = "Street can't be empty")
    @Size(min = 3, message = "Street can't be less than 3.")
    private String street;

    @Pattern(message = "Enter valid postal code", regexp = "^[0-9]{2}-[0-9]{3}")
    @NotEmpty(message = "Postal code can't be empty.")
    private String postalCode;

    @NotEmpty(message = "City can't be empty")
    private String city;

    private String status;
}
