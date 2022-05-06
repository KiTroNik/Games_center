package pl.studia.ecommerence.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @NotEmpty(message = "Product name cant be null.")
    private String name;

    @Lob
    @NotEmpty(message = "Product description cant be null.")
    private String description;

    @DecimalMin(value = "3.0", message = "Price can't be lower than 3$.")
    private Double price;

    @NotEmpty(message = "Product image URL cant be null.")
    private String imageURL;

    public Product(String name, String description, Double price, String imageURL) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageURL = imageURL;
    }
}
