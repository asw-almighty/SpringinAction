package tacos;

import lombok.Data;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@Entity
@RestResource(rel="tacos", path="tacos")
public class Taco extends RepresentationModel<Taco> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date createdAt;

    @NotNull
    @Size(min=5, message="at Least 5 characters long")
    private String name;

    @ManyToMany(targetEntity = Ingredient.class)
    @Size(min=1, message="at Least 1 ingredients")
    private List<Ingredient> ingredients;

    @PrePersist
    void createdAt() {
        this.createdAt = new Date();
    }
}
