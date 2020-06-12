package tacos;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
public class Taco {
    private Long id;
    private Date createdAt;

    @NotNull
    @Size(min=5, message="at Least 5 characters long")
    private String name;

    @NotNull
    @Size(min=1, message="at Least 1 ingredients")
    private List<String> ingredients;
}
