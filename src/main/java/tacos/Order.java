package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.Date;

@Data
public class Order {
    private Long id;
    private Date placedAt;

    @NotBlank(message="required")
    private String name;

    @NotBlank(message="required")
    private String street;

    @NotBlank(message="required")
    private String city;

    @NotBlank(message="required")
    private String state;

    @NotBlank(message="required")
    private String zip;

    @CreditCardNumber(message="InValid Number")
    private String ccNumber;

    @Pattern(regexp="^([1-9]|1[0-2])([\\/])([1-9][0-9])$", message="must be formmated MM/YY")
    private String ccExpiration;

    @Digits(integer=3,fraction=0,message="InValid CVV")
    private String ccCVV;

}
