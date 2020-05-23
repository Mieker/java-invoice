package pl.edu.agh.mwo.invoice.product;

import java.math.BigDecimal;

public class FuelCanister extends Product {

    static final BigDecimal excise = new BigDecimal("5.56");

    public FuelCanister(String name, BigDecimal price) {
        super(name, price, new BigDecimal("0.18"));
    }

    @Override
    public BigDecimal getPriceWithTax() {
        return this.getPrice().add(excise);
    }
}
