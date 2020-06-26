package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.RepresentationModel;
import tacos.Ingredient;
import tacos.Ingredient.Type;

@Getter
public class IngredientResource extends RepresentationModel<Ingredient> {
    private String name;
    private Type type;
    public IngredientResource(Ingredient ingredient){
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }
}
