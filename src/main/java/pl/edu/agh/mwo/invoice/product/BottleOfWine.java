package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class BottleOfWine extends Product {
    
    private final BigDecimal excise = new BigDecimal("5.56"); 
    
    public BottleOfWine(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.19"));
    }
    
    @Override
    public BigDecimal getPriceWithTax() {
        return this.getPrice().multiply(this.getTaxPercent()).add(this.getPrice()).add(excise);
    }
}
