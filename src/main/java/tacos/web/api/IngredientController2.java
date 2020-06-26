package tacos.web.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tacos.Ingredient;
import tacos.data.IngredientRepository;

@RestController
@RequestMapping(path="/ingredientsx", produces="application/json")
@CrossOrigin(origins="/")
public class IngredientController2 {
    private IngredientRepository repo;

    public IngredientController2(IngredientRepository repo){
        this.repo=repo;
    }

    @GetMapping
    public Iterable<Ingredient> allIngredients(){
        return repo.findAll();
    }
}
