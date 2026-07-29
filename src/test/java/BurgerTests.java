import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import praktikum.*;

import static java.lang.String.format;
import static org.junit.Assert.assertEquals;
import static praktikum.IngredientType.SAUCE;

@RunWith(MockitoJUnitRunner.class)
public class BurgerTests {

    private Burger burger;

    @Mock
    private Bun mockBun;

    @Mock
    private Ingredient mockIngredient;

    @Mock
    private Ingredient mockSecondIngredient;

    @Before
    public void setUp(){
        burger = new Burger();
    }

    @Test
    public void setBunTest(){
        burger.setBuns(mockBun);
        assertEquals("Ошибка установления булочки бургера.", mockBun, burger.bun);
    }

    @Test
    public void addIngredientShouldIncreaseTheList(){
        burger.addIngredient(mockIngredient);
        assertEquals("Ошибка увеличения длины списка ингредиентов.", 1, burger.ingredients.size());
    }

    @Test
    public void addIngredientShouldAddElement(){
        burger.addIngredient(mockIngredient);
        assertEquals("Ошибка при добавлении ингредиента в список.", mockIngredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveElement(){
        burger.addIngredient(mockIngredient);
        burger.removeIngredient(0);
        assertEquals("Ошибка удаления элемента из списка ингредиентов.", 0, burger.ingredients.size());

    }

    @Test
    public void moveIngredientTest(){
        burger.addIngredient(mockIngredient);
        burger.addIngredient(mockSecondIngredient);

        burger.moveIngredient(0,1);
        assertEquals("Ошибка перемещения ингредиентов.", mockSecondIngredient, burger.ingredients.get(0));

    }

    @Test
    public void getPriceTest(){
        Mockito.when(mockBun.getPrice()).thenReturn(300.0F);
        Mockito.when(mockIngredient.getPrice()).thenReturn(100.0F);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);
        float expectedPrice = (mockBun.getPrice() * 2) + mockIngredient.getPrice();
        assertEquals("Ошибка расчета цены бургера.", expectedPrice, burger.getPrice(), 0.0001f);
    }

    @Test
    public void getReceptTest(){
        Mockito.when(mockBun.getName()).thenReturn("white bun");
        Mockito.when(mockIngredient.getType()).thenReturn(SAUCE);
        Mockito.when(mockIngredient.getName()).thenReturn("chili sauce");
        Mockito.when(mockBun.getPrice()).thenReturn(400.0F);
        Mockito.when(mockIngredient.getPrice()).thenReturn(200.0F);
        burger.setBuns(mockBun);
        burger.addIngredient(mockIngredient);

        String expectedRecept = format("(==== white bun ====)%n= sauce chili sauce =%n(==== white bun ====)%n%nPrice: %f%n", 1000.000000F);

        assertEquals("Ошибка при составлении рецепта бургера.", expectedRecept, burger.getReceipt());

    }


}
