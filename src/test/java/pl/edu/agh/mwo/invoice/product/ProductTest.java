package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.mwo.invoice.product.Product;

public class ProductTest {
    @Test
    public void testProductNameIsCorrect() {
        Product product = new OtherProduct("buty", new BigDecimal("100.0"));
        Assert.assertEquals("buty", product.getName());
    }

    @Test
    public void testProductPriceAndTaxWithDefaultTax() {
        Product product = new OtherProduct("Ogorki", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.23"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testProductPriceAndTaxWithDairyProduct() {
        Product product = new DairyProduct("Szarlotka", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("0.08"), Matchers.comparesEqualTo(product.getTaxPercent()));
    }

    @Test
    public void testPriceWithTax() {
        Product product = new DairyProduct("Oscypek", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("108"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullName() {
        new OtherProduct(null, new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithEmptyName() {
        new TaxFreeProduct("", new BigDecimal("100.0"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNullPrice() {
        new DairyProduct("Banany", null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testProductWithNegativePrice() {
        new TaxFreeProduct("Mandarynki", new BigDecimal("-1.00"));
    }
    
    @Test
    public void testBottleOfWinePriceAndPriceWithTaxAndExcise() {
        Product product = new BottleOfWine("Bordeaux", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100.0"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("124.56"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
    
    @Test
    public void testFuelCanisterPriceAndPriceWithTaxAndExciseOnRoadDay() {
        FuelCanister.roadDayTaxExemption = true;
        Product product = new FuelCanister("Benzyna 95", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100.0"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("100.0"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
    
    @Test
    public void testFuelCanisterPriceWithTaxWithExcise() {
        FuelCanister.roadDayTaxExemption = false;
        Product product = new FuelCanister("Olej napędowy", new BigDecimal("100.0"));
        Assert.assertThat(new BigDecimal("100.0"), Matchers.comparesEqualTo(product.getPrice()));
        Assert.assertThat(new BigDecimal("123.56"), Matchers.comparesEqualTo(product.getPriceWithTax()));
    }
    
    @Test
    public void testProductEqualsItself() {
        Product product = new OtherProduct("Dętka", new BigDecimal("33.33"));
        Product product2 = new OtherProduct("Opona", new BigDecimal("33.33"));
        Assert.assertTrue(product.equals(product));
        Assert.assertFalse(product.equals(product2));
    }
}
