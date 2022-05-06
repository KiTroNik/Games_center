package pl.studia.ecommerence.dto.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderDto {
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
}
