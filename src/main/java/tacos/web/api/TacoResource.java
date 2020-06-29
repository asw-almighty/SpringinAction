package tacos.web.api;

import lombok.Getter;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import org.springframework.hateoas.server.core.Relation;
import tacos.Ingredient;
import tacos.Taco;
import tacos.web.DesignTacoController;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Relation(value="taco", collectionRelation="tacos")
public class TacoResource extends RepresentationModel<Taco> {
    private final String name;
    private final Date createdAt;
    private final CollectionModel<IngredientResource> ingredients;

    private static final IngredientResourceAssembler ingredientAssembler = new IngredientResourceAssembler();

    public TacoResource(Taco taco) {
        this.name = taco.getName();
        this.createdAt = taco.getCreatedAt();
        this.ingredients = ingredientAssembler.toCollectionModel(taco.getIngredients());
    }
}

