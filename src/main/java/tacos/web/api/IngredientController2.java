package tacos.web.api;

import org.springframework.hateoas.CollectionModel;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping(path="/ingredients", produces="application/json")
@CrossOrigin(origins="/")
public class IngredientController2 {
    private IngredientRepository repo;

    public IngredientController2(IngredientRepository repo){
        this.repo=repo;
    }

    @GetMapping
    public CollectionModel<IngredientResource> allIngredients(){
        Iterable<Ingredient> ingredientList = repo.findAll();
        CollectionModel ingredientCollectionModel = new IngredientResourceAssembler().toCollectionModel(ingredientList);
        CollectionModel<IngredientResource> allIngredientResources= new CollectionModel<IngredientResource>(ingredientCollectionModel, linkTo(methodOn(IngredientController2.class).allIngredients()).withRel("allIngredients"));
        return allIngredientResources;
    }
}
