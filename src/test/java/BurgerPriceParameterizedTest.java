import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import praktikum.Bun;
import praktikum.Burger;
import praktikum.Ingredient;
import praktikum.IngredientType;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;


@RunWith(Parameterized.class)
public class BurgerPriceParameterizedTest {
    private final float bunPrice;
    private final List<Float> ingredientPrices;
    private final float expectedPrice;
    private Burger burger;
    private Bun bun;

    public BurgerPriceParameterizedTest(float bunPrice, List<Float> ingredientPrices
                                            , float expectedPrice){

        this.bunPrice = bunPrice;
        this.ingredientPrices = ingredientPrices;
        this.expectedPrice = expectedPrice;
    }

    @Parameterized.Parameters(name = "Тестовые данные: цена одной булочки бургера = {0}, " +
            "список цен добавленных соусов/ингредиентов = {1}, ожидаемая цена заказа = {2}")
    public static Object[][] parameters(){
        return new Object[][]{
                {150.0f, Arrays.asList(50.0f, 200.0f), 550.0f},
                {200.0f, Arrays.asList(30.0f, 40.0f, 20.0f), 490.0f},
                {0.0f, Arrays.asList(), 0.0f},
                {75.0f, Arrays.asList(25.0f), 175.0f}
        };
    }

    @Before
    public void setUp(){
        burger = new Burger();
        bun = new Bun("simpleBun", bunPrice);
    }

    @Test
    public void burgerPriceTests(){
        burger.setBuns(bun);
        for (Float price : ingredientPrices) {
            Ingredient ingredient = new Ingredient(IngredientType.SAUCE,"ingredientName", price);
            burger.addIngredient(ingredient);
        }
        assertEquals("Ошибка расчета цены бургера.", expectedPrice, burger.getPrice(), 0.01f);
    }

}
