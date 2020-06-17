package tacos;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.persistence.*;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name="Taco_Order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date placedAt;

    @NotBlank(message="required")
    private String deliveryName;

    @NotBlank(message="required")
    private String deliveryStreet;

    @NotBlank(message="required")
    private String deliveryCity;

    @NotBlank(message="required")
    private String deliveryState;

    @NotBlank(message="required")
    private String deliveryZip;

    @CreditCardNumber(message="InValid Number")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message="must be formmated MM/YY")
    private String ccExpiration;

    @Digits(integer=3,fraction=0,message="InValid CVV")
    private String ccCVV;

    @ManyToMany(targetEntity=Taco.class)
    private List<Taco> tacos = new ArrayList<>();

    public void addDesign(Taco design){
        this.tacos.add(design);
    }

    @PrePersist
    void placedAt(){
        this.placedAt=new Date();
    }
}
