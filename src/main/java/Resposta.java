import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Resposta {
    private static WebDriver driver;
    private static final String URL = "https://igorsmasc.github.io/praticando_selects_radio_checkbox/";

    @BeforeAll
    public static void beforeAll() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver = new ChromeDriver(options);
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1366, 768));
        driver.get(URL);
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    }

    @Test
    public void testRadioButton() {

        WebElement radioButton = driver.findElement(By.id("medio"));

        radioButton.click();

        Assertions.assertTrue(radioButton.isSelected());
    }

    @Test
    public void testCheckBox() {
//        WebElement checkForno = driver.findElement(By.id("forno"));
        WebElement checkMicroondas = driver.findElement(By.id("microondas"));
        checkMicroondas.click();

        Assertions.assertTrue(checkMicroondas.isSelected());
    }

    @Test
    public void testSelectsByIndex() {
        WebElement select = driver.findElement(By.id("categoria"));
        Select categorias = new Select(select);
        categorias.selectByIndex(1);

        Assertions.assertEquals("Almoço e Janta", categorias.getFirstSelectedOption().getText());
    }

    @Test
    public void testSelectsByValue() {
        WebElement select = driver.findElement(By.id("categoria"));
        Select categorias = new Select(select);
        categorias.selectByValue("sobremesa");

        Assertions.assertEquals("Sobremesa", categorias.getFirstSelectedOption().getText());
    }

    @Test
    public void testSelectsByVisibleText() {
        WebElement select = driver.findElement(By.id("categoria"));
        Select categorias = new Select(select);
        categorias.selectByVisibleText("Almoço e Janta");

        Assertions.assertEquals("Almoço e Janta", categorias.getFirstSelectedOption().getText());
    }

    @Test
    public void testSelectValues() {
        WebElement select = driver.findElement(By.id("categoria"));
        Select categorias = new Select(select);

        List<WebElement> elements = categorias.getOptions();
        Assertions.assertEquals(4, elements.size());

        List<String> categoriasText = categorias.getOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> categoriasEsperadas = Arrays.asList("Selecione uma opção", "Almoço e Janta", "Sobremesa", "Lanche");

        Assertions.assertArrayEquals(categoriasEsperadas.toArray(), categoriasText.toArray());
    }

    @Test
    public void testSelectsMultiplos() {
        WebElement select = driver.findElement(By.id("ingredientes"));
        Select ingredientes = new Select(select);
        ingredientes.selectByVisibleText("Frango");
        ingredientes.selectByVisibleText("Farinha");

        Assertions.assertEquals(2, ingredientes.getAllSelectedOptions().size());

        List<String> ingredientesText = ingredientes.getAllSelectedOptions().stream().map(WebElement::getText)
                .collect(Collectors.toList());

        List<String> ingredientesEsperados = Arrays.asList("Frango", "Farinha");


        Assertions.assertEquals(2, ingredientes.getAllSelectedOptions().size());
        Assertions.assertArrayEquals(ingredientesText.toArray(), ingredientesEsperados.toArray());
    }
}